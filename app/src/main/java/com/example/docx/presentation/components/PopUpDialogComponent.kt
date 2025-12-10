package com.example.docx.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.docx.ui.theme.GradientBasicAlertDialogBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpDialogComponent(
    text: String,
    state: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit
) {
    if (state) {
        BasicAlertDialog(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(GradientBasicAlertDialogBackground),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(6.dp),
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = text, color = Color.White)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = onDismissRequest
                        ) {
                            Text("Cancel")
                        }
                        TextButton(
                            onClick = onConfirmClick
                        ) {
                            Text("Yes")
                        }
                    }
                }
            }
        )
    }
}