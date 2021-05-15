package com.pramod.newsapp.repository

import com.pramod.newsapp.mapper.ArticleNEMapper
import com.pramod.newsapp.model.Article
import com.pramod.newsapp.network.NewsApiService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@ViewModelScoped
class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val articleNEMapper: ArticleNEMapper
) {

    suspend fun getTopHeadlines(
        country: String,
        category: String
    ): List<Article> {
        return newsApiService.getTopHeadlines(
            country,
            category
        ).articleNEList.map { articleNEMapper.fromEntity(it) }
    }

}