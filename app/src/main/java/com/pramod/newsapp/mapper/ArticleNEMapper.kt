package com.pramod.newsapp.mapper

import com.pramod.newsapp.model.Article
import com.pramod.newsapp.network.ArticleNE
import com.pramod.newsapp.util.EntityMapper
import javax.inject.Inject

class ArticleNEMapper @Inject constructor(): EntityMapper<ArticleNE, Article> {
    override fun fromEntity(entity: ArticleNE): Article {
        return Article(
            entity.author,
            entity.title,
            entity.description,
            entity.urlToImage,
            entity.content,
            entity.publishedAt
        )
    }

    override fun toEntity(domain: Article): ArticleNE {
        return ArticleNE(
            domain.author,
            domain.title,
            domain.description,
            domain.urlToImage,
            domain.content,
            domain.publishedAt
        )
    }


}