package com.takaobrog.roomcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.takaobrog.roomcompose.presentation.route.ScreenRoute
import com.takaobrog.roomcompose.presentation.route.taskCreateRoute
import com.takaobrog.roomcompose.presentation.route.taskDetailRoute
import com.takaobrog.roomcompose.presentation.route.taskListRoute
import com.takaobrog.roomcompose.presentation.ui.theme.DemoTaskAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTaskAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.TaskList.route
                ) {
                    taskListRoute(navController = navController)
                    taskCreateRoute(navController = navController)
                    taskDetailRoute(navController = navController)
                }
            }
        }
    }
}