package kz.android.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kz.android.domain.model.Article
import kz.android.domain.usecase.GetNewsUseCase
import kz.android.domain.usecase.SaveNewsUseCase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val getNewsUseCase = mockk<GetNewsUseCase>()
    private val saveNewsUseCase = mockk<SaveNewsUseCase>()
    private lateinit var viewModel: NewsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NewsViewModel(getNewsUseCase, saveNewsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchNews should update news LiveData`() = runTest(testDispatcher) {
        coEvery { getNewsUseCase.invoke(any(), any()) } returns listOf(
            Article(
                title = "Test",
                author = "Author",
                content = "Content",
                url = "url",
                publishedAt = "2023-11-20"
            )
        )

        viewModel.fetchNews("query", "2023-11-20")
        testDispatcher.scheduler.advanceUntilIdle() // Убедиться, что все корутины завершены

        assertEquals("Test", viewModel.news.value?.first()?.title)
    }
}

