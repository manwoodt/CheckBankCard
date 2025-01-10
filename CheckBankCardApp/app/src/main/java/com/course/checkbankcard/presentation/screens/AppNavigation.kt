package com.course.checkbankcard.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

NavHost(navController=navController, startDestination = "main_screen"){
    composable("main_screen"){
        MainScreen(navController)
    }


    composable("history_screen"){
        HistoryScreen(navController)
    }

}

}