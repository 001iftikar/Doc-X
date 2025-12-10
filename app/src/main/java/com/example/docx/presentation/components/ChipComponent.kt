package com.example.docx.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChipComponent(
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF046322)
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
        )
    }
}