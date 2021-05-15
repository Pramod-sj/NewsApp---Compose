package com.pramod.newsapp.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pramod.newsapp.model.Article
import com.pramod.newsapp.network.NewsApiService
import com.pramod.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Pramod on 07,May,2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsArticle: MutableState<List<Article>> = mutableStateOf(arrayListOf())

    val newsArticle: State<List<Article>>
        get() = _newsArticle

    private val _loading: MutableState<Boolean> = mutableStateOf(false)

    val loading: State<Boolean>
        get() = _loading

    init {

        viewModelScope.launch {
            try {
                _loading.value = true
                _newsArticle.value = newsRepository.getTopHeadlines("in", "science")
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                e.printStackTrace()
            }
        }

    }


    fun getArticleById(articleId: String): Article? {
        return _newsArticle.value.find { article -> article.publishedAt == articleId }
    }


}