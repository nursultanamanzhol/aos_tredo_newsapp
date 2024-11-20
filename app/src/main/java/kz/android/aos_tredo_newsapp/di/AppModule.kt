package kz.android.aos_tredo_newsapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.android.aos_tredo_newsapp.R
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("NewsApiKey")
    fun provideApiKey(@ApplicationContext context: Context): String {
        return context.getString(R.string.news_api_key)
    }
}
