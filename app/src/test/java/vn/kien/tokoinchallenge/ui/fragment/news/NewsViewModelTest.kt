package vn.kien.tokoinchallenge.ui.fragment.news

import org.junit.Test

import org.koin.core.inject
import org.koin.test.KoinTest
import vn.kien.tokoinchallenge.data.repository.AppRepository

class NewsViewModelTest: KoinTest {
    private val repo by inject<AppRepository>()
    private val vm = NewsViewModel(repo)
}