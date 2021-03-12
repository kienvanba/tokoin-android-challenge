package vn.kien.tokoinchallenge.di

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.kien.tokoinchallenge.BuildConfig
import vn.kien.tokoinchallenge.data.network.ApiServices
import vn.kien.tokoinchallenge.di.Properties.TIME_OUT
import vn.kien.tokoinchallenge.util.TokoinConstants
import vn.kien.tokoinchallenge.util.TokoinLogger
import java.util.concurrent.TimeUnit

val networkModule = module(override = true) {
    single { createOkHttpCache(get()) }
    single(named("logging")) { createLoggingInterceptor() }
    single(named("header")) { createHeaderInterceptor() }
    single { createOkHttpClient(get(), get(named("logging")), get(named("header"))) }
    single { createAppRetrofit(get()) }
    single { createApiService(get()) }
}

object Properties {
    const val TIME_OUT = 10
}

fun createOkHttpCache(context: Context): Cache {
    val size = (10 * 1024 * 1024).toLong() // 10 Mb
    return Cache(context.cacheDir, size)
}

fun createLoggingInterceptor(): Interceptor {
    val logging = HttpLoggingInterceptor { message -> TokoinLogger.i(message) }
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return logging
}

fun createHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .header("Authorization", TokoinConstants.NewsApiKey)
            .method(request.method(), request.body())
            .build()
        chain.proceed(newRequest)
    }
}

fun createOkHttpClient(
    cache: Cache,
    logging: Interceptor,
    header: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(header)
        .addInterceptor(logging)
        .build()
}

fun createAppRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .build()
}

fun createApiService(retrofit: Retrofit): ApiServices {
    return retrofit.create(ApiServices::class.java)
}