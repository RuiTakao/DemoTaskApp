package com.takaobrog.roomcompose.presentation.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.taskEditRoute(navController: NavHostController) {
    composable(
        route = ScreenRoute.TaskEdit.route + "/{uid}",
        arguments = listOf(navArgument("uid") { type = NavType.IntType })
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
        ) { paddingValue ->
            Column(modifier = Modifier.padding(paddingValues = paddingValue)) {
                Text(text = "編集画面")
            }
        }
    }
}