package com.example.docx.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
 sealed interface Route : NavKey {

     @Serializable
     data object DashboardScreen : Route

    @Serializable
    data object StatisticsScreen : Route

    @Serializable
    data object HistoryScreen : Route
    @Serializable
    data class DetailedHistoryScreen(val appointmentId: Int) : Route

    @Serializable
    data object ScheduleScreen : Route

    @Serializable
    data object ProfileScreen : Route
}