package com.pramod.newsapp.home.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.coil.rememberCoilPainter
import com.pramod.newsapp.R
import androidx.navigation.compose.navigate
import com.pramod.newsapp.home.MainViewModel
import com.pramod.newsapp.home.ROUTE_ARTICLE_DETAILS
import com.pramod.newsapp.home.ROUTE_ARTICLE_LIST

/**
 * Created by Pramod on 07,May,2021
 */

@ExperimentalAnimationApi
@Preview
@Composable
fun ArticleListScreen(navController: NavController, viewModel: MainViewModel) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)

    val loading = viewModel.loading.value

    val newsArticleList = viewModel.newsArticle.value


    if (loading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(strokeWidth = 3.dp, modifier = Modifier.size(24.dp))
        }
    } else {
        LazyColumn {
            itemsIndexed(newsArticleList) { position, article ->
                Card(
                    shape = RoundedCornerShape(6.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .padding(
                            PaddingValues(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = {
                                navController.navigate(
                                    "articles/${article.publishedAt}"
                                )
                            })
                    ) {

                        Image(
                            painter = rememberCoilPainter(
                                request = article.urlToImage,
                                previewPlaceholder = R.drawable.drawable_placeholder_news,
                                fadeIn = true
                            ),
                            contentDescription = "News Article Placeholder",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            article.title ?: "",
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 4.dp,
                                bottom = 4.dp,
                                end = 8.dp
                            ),
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            article.description ?: "",
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 4.dp,
                                bottom = 4.dp,
                                end = 8.dp
                            ),
                            style = MaterialTheme.typography.caption
                        )
                    }

                }
            }
        }
    }
}