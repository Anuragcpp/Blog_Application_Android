package com.content.blogapplication.dashboard.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.content.blogapplication.dashboard.screens.homeScreen.view.HomeScreen
import com.content.blogapplication.mainactivty.rootNavigation.DashboardGraphRoute

fun NavGraphBuilder.dashboardNavGraph(
    innerPadding : PaddingValues,
    navHostController: NavHostController
){

    navigation<DashboardGraphRoute>(
        startDestination = HomeScreen
    ){

        composable<HomeScreen> {
            HomeScreen(innerPadding = innerPadding)
        }

    }

}