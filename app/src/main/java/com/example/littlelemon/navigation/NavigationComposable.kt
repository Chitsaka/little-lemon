package com.example.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.screens.Onboarding
import com.example.littlelemon.ui.screens.HomeScreen
import com.example.littlelemon.ui.screens.ProfileScreen
import androidx.navigation.compose.NavHost


@Composable
fun Navigation(navController: NavHostController,
               modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val sharedPreferences = remember {
        context.getSharedPreferences(
            "LittleLemonPrefs",
            Context.MODE_PRIVATE
        )
    }

    val firstName = sharedPreferences.getString("firstName", null)
    val lastName = sharedPreferences.getString("lastName", null)
    val email = sharedPreferences.getString("email", null)

    val startDestination = if (
        !firstName.isNullOrBlank() &&
        !lastName.isNullOrBlank() &&
        !email.isNullOrBlank()
    ) {
        Destinations.Home.route
    } else {
        Destinations.Onboarding.route
    }


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(Destinations.Onboarding.route) {
            Onboarding(
                onLoginClick = {
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(Destinations.Onboarding.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Destinations.Home.route) {
            HomeScreen(
                onProfileClick = {
                    navController.navigate(Destinations.Profile.route)
                }
            )
        }

        composable(Destinations.Profile.route) {
            ProfileScreen(
                onHomeClick = {
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(Destinations.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onLogoutClick = {
                    sharedPreferences.edit {
                        clear()
                    }

                    navController.navigate(Destinations.Onboarding.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = modifier
            )
        }
    }
}





