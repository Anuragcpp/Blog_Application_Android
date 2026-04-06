package com.content.blogapplication.rootNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.content.blogapplication.auth.navigation.authNavGraph
import com.content.blogapplication.auth.screen.signup.view.SignUpScreen
import com.content.blogapplication.dashboard.navigation.dashboardNavGraph
import com.content.blogapplication.dashboard.screens.homeScreen.view.HomeScreen

@Composable
fun RootNavGraph(navController : NavHostController, innerPadding : PaddingValues) {
    /*
    NavHost(navController, startDestination = "signup"){
        composable("signup") { SignUpScreen(innerPadding) {
            navController.navigate("homeScreen")
        } }
        composable("homeScreen") { HomeScreen(innerPadding) }
    }

     */

    NavHost(navController = navController, startDestination = AuthGraphRoute){
        authNavGraph(innerPadding,navController)
        dashboardNavGraph(innerPadding,navController)
    }
}