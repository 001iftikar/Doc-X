package com.example.docx.presentation.dashboard_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.docx.domain.model.Appointment
import com.example.docx.presentation.components.PopUpDialogComponent
import com.example.docx.utils.toReadableTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.Instant

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    val currentTime: Long by produceState(initialValue = Instant.now().toEpochMilli()) {
        while (isActive) {
            value = Instant.now().toEpochMilli()
            delay(1000L)
        }
    }
    var isPopUpDialogOpen by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
               // .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Now: ${currentTime.toReadableTime()}"
            )

            EarliestAppointmentCard(
                appointment = l2.first {
                    it.isBooked && !it.isSeen
                }
                // todo filter in the view model for real data and just show here
            )

            TextButton(
                onClick = {
                    isPopUpDialogOpen = true
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            ) {
                Text(
                    text = "Mark as seen",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        PopUpDialogComponent(
            text = "Mark this patient as seen?",
            state = isPopUpDialogOpen,
            onDismissRequest = { isPopUpDialogOpen = false },
            onConfirmClick = {
                // todo mark patient as seen and simulate loading the next earliest patient with text "Loading next patient"
                isPopUpDialogOpen = false
            }
        )
    }
}

@Composable
private fun EarliestAppointmentCard(
    appointment: Appointment
) {
    Column(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your next booked appointment",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(6.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF5568B5))
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${appointment.start.toReadableTime()} to ${appointment.end.toReadableTime()}",
                    color = Color.Green,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = appointment.appointedBy
                )

                appointment.illness?.let {
                    Text(
                        text = it,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

private val l2 = listOf(
    Appointment(
        start = 1,
        end = Instant.now().toEpochMilli(),
        appointedBy = "1st",
        isBooked = false,
        illness = "Anxiety"
    ),
    Appointment(
        start = 2,
        end = Instant.now().toEpochMilli(),
        appointedBy = "2nd",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = 5,
        end = Instant.now().toEpochMilli(),
        appointedBy = "5th",
        isBooked = true,
        illness = "Depression, Anxiety"
    ),
    Appointment(
        start = 4,
        end = Instant.now().toEpochMilli(),
        appointedBy = "4th",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = 7,
        end = Instant.now().toEpochMilli(),
        appointedBy = "7th",
        isBooked = true,
        illness = null
    ),
    Appointment(
        start = 3,
        end = Instant.now().toEpochMilli(),
        appointedBy = "3rd",
        isBooked = true,
        illness = "Headache, Fever"
    )
).sortedWith(
    compareByDescending<Appointment> { it.isBooked }
        .thenBy { it.start }
)






















