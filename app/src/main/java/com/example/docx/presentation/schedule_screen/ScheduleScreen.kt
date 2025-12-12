package com.example.docx.presentation.schedule_screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docx.domain.model.Appointment
import com.example.docx.presentation.components.PopUpDialogComponent
import com.example.docx.ui.theme.DarkGreen
import com.example.docx.ui.theme.DarkRed
import com.example.docx.ui.theme.DarkYellow
import com.example.docx.utils.toPrettyDate
import com.example.docx.utils.toReadableTime
import java.time.Instant

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier
) {
    var isRequestAcceptPopUpOpen by remember { mutableStateOf(false) }
    var requestAcceptPopUpText by remember { mutableStateOf("") }
    var isAcceptButtonClicked by remember { mutableStateOf(false) }
    val onAcceptClick = {
        isAcceptButtonClicked = true
        requestAcceptPopUpText = "Do you want to book this patient?"
        isRequestAcceptPopUpOpen = true
    }

    val onDeclineClick = {
        requestAcceptPopUpText = "Do you want to decline this patient?"
        isRequestAcceptPopUpOpen = true
    }
    Scaffold(
        modifier = modifier
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text("Today: ${Instant.now().toEpochMilli().toPrettyDate()}")
            }

            items(dummyAppointments) { appointment ->
                AppointmentCard(
                    appointment = appointment,
                    onAccept = onAcceptClick,
                    onDecline = onDeclineClick
                )
            }
        }

        PopUpDialogComponent(
            text = requestAcceptPopUpText,
            state = isRequestAcceptPopUpOpen,
            onDismissRequest = {
                isRequestAcceptPopUpOpen = false
            },
            onConfirmClick = {
                if (isAcceptButtonClicked) {
                    // todo mark the patient as booked and in db isBooked = true
                    Log.d("Request", "Simulating marked as booked")
                    isAcceptButtonClicked = false
                    isRequestAcceptPopUpOpen = false
                } else {
                    // todo remove the patient from appointment list
                    Log.d("Request", "Simulating patient delete from db")
                    isRequestAcceptPopUpOpen = false
                }
            }
        )
    }
}

@Composable
private fun AppointmentCard(
    appointment: Appointment,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (appointment.isBooked) {
                DarkRed
            } else if (appointment.appointedBy == null) {
                DarkGreen
            } else {
                DarkYellow
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${appointment.start.toReadableTime()} to ${appointment.end.toReadableTime()}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                if (!appointment.isBooked && appointment.appointedBy != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onAccept)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Done,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.fillMaxSize()
                            )
                        }


                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onDecline)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.fillMaxSize()
                            )
                        }


                    }
                }
            }
            Text(
                text = if (appointment.isBooked) "Booked by: ${appointment.appointedBy}"
                else if (appointment.appointedBy != null) "Appointed By: ${appointment.appointedBy}"
                else "Free",
                color = Color.White
            )

            appointment.illness?.let { reason ->
                Text(
                    text = reason,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


// The epoch millis will come from db, so I am just hard coding to current time
private val dummyAppointments = listOf(
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Micheal",
        isBooked = false,
        illness = "Anxiety"
    ),
    Appointment(
        start = 1765180200000,
        end = Instant.now().toEpochMilli(),
        appointedBy = "Adam",
        isBooked = true,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = null,
        isBooked = true,
        illness = "Depression, Anxiety"
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = null,
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = null,
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Joe",
        isBooked = false,
        illness = null
    )
    )
    .filter { !it.isSeen }
    .sortedWith(
    compareByDescending<Appointment> { it.isBooked }
        .thenBy { it.start } // todo Do these inside viewmodel in Default dispatcher
)
















