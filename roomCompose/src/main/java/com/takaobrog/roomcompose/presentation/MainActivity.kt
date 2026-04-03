package com.takaobrog.roomcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takaobrog.roomcompose.presentation.task_list.TaskListScreen
import com.takaobrog.roomcompose.presentation.task_list.TaskListViewModel
import com.takaobrog.roomcompose.presentation.tasl_create.TaskCreateScreen
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
                val taskListViewModel: TaskListViewModel = hiltViewModel()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.TaskList.route
                ) {
                    composable(route = ScreenRoute.TaskList.route) {
                        val list by taskListViewModel.taskList.collectAsState(initial = emptyList())
                        TaskListScreen(
                            list = list,
                            onClick = { navController.navigate(ScreenRoute.TaskCreate.route) })
                    }
                    composable(route = ScreenRoute.TaskCreate.route) {
                        TaskCreateScreen(onClick = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}