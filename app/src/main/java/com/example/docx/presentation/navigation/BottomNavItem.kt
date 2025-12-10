package com.example.docx.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val title: String
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Route.DashboardScreen to BottomNavItem(
        icon = Icons.Default.Dashboard,
        title = "Dashboard"
    ),
    Route.StatisticsScreen to BottomNavItem(
        icon = Icons.Default.Analytics,
        title = "Statistics"
    ),
    Route.HistoryScreen to BottomNavItem(
        icon = Icons.Default.History,
        title = "History"
    ),
    Route.ScheduleScreen to BottomNavItem(
        icon = Icons.Default.Schedule,
        title = "Schedule"
    ),
    Route.ProfileScreen to BottomNavItem(
        icon = Icons.Default.AccountCircle,
        title = "Dashboard"
    )
)
