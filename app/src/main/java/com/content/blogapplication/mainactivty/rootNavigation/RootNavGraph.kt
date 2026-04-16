package com.content.blogapplication.mainactivty.rootNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.content.blogapplication.auth.navigation.authNavGraph
import com.content.blogapplication.dashboard.navigation.dashboardNavGraph
import com.content.blogapplication.mainactivty.state.AuthState
import com.content.blogapplication.mainactivty.viewModel.MainViewModel
import com.content.blogapplication.splashScreen.SplashScreen

@Composable
fun RootNavGraph(
    navController : NavHostController,
    innerPadding : PaddingValues,
    viewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = SplashScreenRoute){

        composable < SplashScreenRoute> {
            val authState by viewModel.authState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.checkAuthState()
            }

            LaunchedEffect(authState) {
                when(authState) {

                    AuthState.Authenticated -> {
                        navController.navigate(DashboardGraphRoute){
                            popUpTo(SplashScreenRoute) {inclusive = true}
                        }
                    }

                    AuthState.Unauthenticated -> {
                        navController.navigate(AuthGraphRoute){
                            popUpTo(SplashScreenRoute) {inclusive = true}
                        }
                    }

                    else -> Unit

                }
            }

            SplashScreen()

        }

        authNavGraph(innerPadding,navController)
        dashboardNavGraph(innerPadding,navController)
    }

}