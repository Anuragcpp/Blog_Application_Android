package com.content.blogapplication.auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.content.blogapplication.auth.screen.signup.view.SignUpScreen
import com.content.blogapplication.dashboard.navigation.HomeScreen
import com.content.blogapplication.rootNavigation.AuthGraphRoute

fun NavGraphBuilder.authNavGraph(
    innerPadding : PaddingValues,
    navHostController: NavHostController
){

    navigation<AuthGraphRoute>(
        startDestination = SignUpRoute
    ){
        composable<SignUpRoute> {
            SignUpScreen(innerPadding = innerPadding) {
               navHostController.navigate(HomeScreen) {
                   popUpTo(AuthGraphRoute){ inclusive = true}
               }
            }
        }
    }

}