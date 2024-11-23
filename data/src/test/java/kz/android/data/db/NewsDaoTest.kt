package kz.android.data.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest {

    private lateinit var database: NewsDatabase
    private lateinit var newsDao: NewsDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        newsDao = database.newsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveAndRetrieveNews() = runBlocking {
        val article = SavedNews(
            url = "https://example.com",
            title = "Test Article",
            author = "Test Author",
            content = "This is a test article.",
            publishedAt = "2023-11-23"
        )

        newsDao.saveNews(article)
        val savedArticles = newsDao.getAllSavedNews()
        assertEquals(article, savedArticles.first())
    }
}
