package vn.kien.tokoinchallenge.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module(override = true) {
    single { androidApplication().resources }
}