package com.example.docx.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.docx.presentation.appointment_history_screen.AppointmentHistoryDetailsScreen
import com.example.docx.presentation.appointment_history_screen.AppointmentHistoryScreen
import com.example.docx.presentation.dashboard_screen.DashboardScreen
import com.example.docx.presentation.profile_screen.ProfileScreen
import com.example.docx.presentation.schedule_screen.ScheduleScreen
import com.example.docx.presentation.statistics_screen.StatisticsScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val navigationState = rememberNavigationState(
        startRoute = Route.DashboardScreen,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )

    val navigator = remember {
        Navigator(state = navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = { key ->
                    navigator.navigate(key)
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<Route.DashboardScreen> {
                        DashboardScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    entry<Route.StatisticsScreen> {
                        StatisticsScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    entry<Route.HistoryScreen> {
                        AppointmentHistoryScreen(
                            modifier = Modifier.fillMaxSize(),
                            onClick = {
                                navigator.navigate(Route.DetailedHistoryScreen(it))
                            }
                        )
                    }
                    entry<Route.DetailedHistoryScreen> {
                        AppointmentHistoryDetailsScreen(modifier = Modifier.fillMaxSize(), appointmentHistoryId = it.appointmentId)
                    }
                    entry<Route.ScheduleScreen> {
                        ScheduleScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    entry<Route.ProfileScreen> {
                        ProfileScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )
        )
    }
}