package com.pramod.newsapp.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.pramod.newsapp.home.screens.*
import okhttp3.Route
import kotlin.reflect.full.isSubclassOf

const val ROUTE_ARTICLE_LIST = "articles"

const val ROUTE_ARTICLE_DETAILS = "articles/"

const val ROUTE_ARTICLE_DETAILS_PARAM = "{articleId}"


@ExperimentalAnimationApi
@Composable
fun NewsNavigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_ARTICLE_LIST) {
        composable(ROUTE_ARTICLE_LIST) {
            ArticleListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = ROUTE_ARTICLE_DETAILS + ROUTE_ARTICLE_DETAILS_PARAM
        ) {
            val articleId = it.arguments?.getString("articleId")!!
            ArticleScreen(
                navController = navController,
                viewModel = viewModel,
                articleId = articleId
            )
        }
    }
}
