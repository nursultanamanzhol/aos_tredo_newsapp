package kz.android.aos_tredo_newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.android.aos_tredo_newsapp.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @Singleton
    @Named("NewsApiKey")
    fun provideNewsApiKey(): String = BuildConfig.NEWS_API_KEY
}
