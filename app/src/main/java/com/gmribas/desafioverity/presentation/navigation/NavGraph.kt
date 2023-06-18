package com.gmribas.modalpresentation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmribas.desafioverity.presentation.navigation.Screens
import com.gmribas.modalpresentation.presentation.contactList.ContactListScreen
import com.gmribas.modalpresentation.presentation.contactDetails.ContactDetailsScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screens.ContactList.route
    )
    {
        composable(Screens.ContactList.route) {
            ContactListScreen(navController)
        }
        composable(
            Screens.ContactDetails.route + "/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) {
            ContactDetailsScreen(navController)
        }
    }
}