package vn.kien.tokoinchallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import vn.kien.tokoinchallenge.di.appModule
import vn.kien.tokoinchallenge.di.networkModule
import vn.kien.tokoinchallenge.di.repositoryModule
import vn.kien.tokoinchallenge.di.viewModelModule

class TokoinApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@TokoinApp)
            modules(modules)
        }
    }

    private val modules = listOf(
        appModule,
        networkModule,
        repositoryModule,
        viewModelModule
    )
}