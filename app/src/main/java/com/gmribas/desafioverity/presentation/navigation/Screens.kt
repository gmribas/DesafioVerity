package com.gmribas.desafioverity.presentation.navigation

sealed class Screens(val route: String) {

    object UserList: Screens("user_list")

    object UserDetails: Screens("user_details")
}