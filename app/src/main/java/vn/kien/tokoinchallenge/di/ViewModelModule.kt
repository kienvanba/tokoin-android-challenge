package vn.kien.tokoinchallenge.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.kien.tokoinchallenge.ui.activity.main.MainViewModel
import vn.kien.tokoinchallenge.ui.fragment.detail.DetailViewModel
import vn.kien.tokoinchallenge.ui.fragment.home.HomeViewModel
import vn.kien.tokoinchallenge.ui.fragment.news.NewsViewModel
import vn.kien.tokoinchallenge.ui.fragment.profile.ProfileViewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}