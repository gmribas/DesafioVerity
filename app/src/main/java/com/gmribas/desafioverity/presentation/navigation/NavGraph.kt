package com.gmribas.desafioverity.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmribas.desafioverity.presentation.screens.userdetails.UserDetailsScreen
import com.gmribas.desafioverity.presentation.screens.userlist.UserListScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screens.UserList.route
    )
    {
        composable(Screens.UserList.route) {
            UserListScreen(navController)
        }
        composable(
            Screens.UserDetails.route + "/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) {
            UserDetailsScreen(navController)
        }
    }
}