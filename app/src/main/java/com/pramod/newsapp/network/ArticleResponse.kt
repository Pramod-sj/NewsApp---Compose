package com.pramod.newsapp.network

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("articles")
    val articleNEList: List<ArticleNE>,
)