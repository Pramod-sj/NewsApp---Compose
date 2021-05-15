package com.pramod.newsapp.home.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.coil.rememberCoilPainter
import com.pramod.newsapp.home.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Composable
fun ArticleScreen(navController: NavController, viewModel: MainViewModel, articleId: String) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry.value?.arguments?.getString(KEY_ROUTE)

    val article = viewModel.getArticleById(articleId = articleId)!!

    Log.i("TAG", "ArticleScreen: $currentRoute == articles/$articleId")

    val isVisible = remember {
        false
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberCoilPainter(request = article.urlToImage),
            contentDescription = "Article image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = article.title ?: "",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = article.content ?: "",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        )

    }



}






