package vn.kien.tokoinchallenge.ui.fragment.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import vn.kien.tokoinchallenge.data.repository.AppRepository
import vn.kien.tokoinchallenge.di.networkModule
import vn.kien.tokoinchallenge.di.repositoryModule
import vn.kien.tokoinchallenge.getOrAwaitValue
import vn.kien.tokoinchallenge.model.User

@RunWith(JUnit4::class)
class NewsViewModelTest : KoinTest {
    private val repo by inject<AppRepository>()
    private val vm = NewsViewModel(repo)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(listOf(
            networkModule,
            repositoryModule,
        ))
    }

    @Test
    fun loadCustoms_noPreference_wrongParam() {
        vm.type = NewsType.Custom
        vm.load(0)
        val result = vm.itemList.getOrAwaitValue()
        assert(result.isNullOrEmpty())
    }

    @Test
    fun loadCustoms() {
        vm.type = NewsType.Custom
        repo.user = User("", "bitcoin")
        vm.load(1)
        val result = vm.itemList.getOrAwaitValue()
        assert(result.isNotEmpty())
    }
}