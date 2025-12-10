package com.example.docx.presentation.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey

@Composable
fun AppNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (Route) -> Unit
) {
    BottomAppBar() {
        TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
            NavigationBarItem(
                selected = topLevelDestination == selectedKey,
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = data.icon,
                        contentDescription = data.title
                    )
                }
            )
        }
    }
}