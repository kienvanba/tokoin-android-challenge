package vn.kien.tokoinchallenge.di

import org.koin.dsl.module
import vn.kien.tokoinchallenge.ui.activity.main.MainViewModel
import vn.kien.tokoinchallenge.ui.fragment.home.HomeViewModel

val viewModelModule = module {
    single { MainViewModel() }
    single { HomeViewModel() }
}