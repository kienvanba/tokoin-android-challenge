package vn.kien.tokoinchallenge.di

import com.google.gson.Gson
import org.koin.dsl.module
import vn.kien.tokoinchallenge.data.local.AppPref
import vn.kien.tokoinchallenge.data.local.DataTransferHelper
import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.data.repository.AppRepositoryImpl

val repositoryModule = module(override = true) {
    single { Gson() }
    single { AppPref(get(), get()) }
    single { DataTransferHelper() }
    single<AppRepository> { AppRepositoryImpl(get(), get()) }
}