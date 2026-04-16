package com.content.blogapplication.auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.content.blogapplication.auth.screen.SignInScreen
import com.content.blogapplication.auth.screen.SignUpScreen
import com.content.blogapplication.dashboard.navigation.HomeScreen
import com.content.blogapplication.mainactivty.rootNavigation.AuthGraphRoute

fun NavGraphBuilder.authNavGraph(
    innerPadding : PaddingValues,
    navHostController: NavHostController
){

    navigation<AuthGraphRoute>(
        startDestination = SignUpRoute
    ){
        composable<SignUpRoute> {
            SignUpScreen(
                innerPadding = innerPadding,
                navigateToSingInScreen = {
                    navHostController.navigate(SignInRoute){
                        popUpTo (SignUpRoute){inclusive = true}
                    }
                }
            ) {
               navHostController.navigate(HomeScreen) {
                   popUpTo(AuthGraphRoute){ inclusive = true}
               }
            }
        }

        composable  < SignInRoute> {
            SignInScreen(innerPadding = innerPadding) {
                navHostController.navigate(HomeScreen){
                    popUpTo(AuthGraphRoute) { inclusive = true }
                }
            }
        }
    }

}