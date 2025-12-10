package com.example.docx.presentation.appointment_history_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.docx.domain.model.Appointment
import com.example.docx.utils.toPrettyDate
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentHistoryScreen(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val dummyAppoHistoryId = 67

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Appointments History",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyList) { appo ->
                HistoryCard(
                    appo,
                    onClick = {
                        onClick(dummyAppoHistoryId)
                    }
                )
            }
        }
    }
}


// todo query the appointment for 'this' history id, when clicked show the appointment with the full appointment history
// since in supabase you will point to the foreign key, I did not do appointment: Appointment in the history model
@Composable
private fun HistoryCard(
    appointment: Appointment,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
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
                text = "Date: ${appointment.appointedOn.toPrettyDate()}",
                color = Color.White
            )
            Text(
                text = appointment.appointedBy,
                color = Color.White
            )

            appointment.illness?.let {
                Text(
                    text = it,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            }
        }
    }
}

// todo in VM or repo filter the list first with isSeen == true
// and sort by desc date
private val dummyList = listOf(
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "John Doe",
        isBooked = true,
        isSeen = true,
        illness = "Pagal"
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Me",
        isBooked = true,
        isSeen = true,
        illness = "Pagal"
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Her",
        isBooked = true,
        isSeen = true,
        illness = "Manipulator"
    ),
    Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Micheal",
        isBooked = true,
        isSeen = true,
        illness = "Pagal"
    )
)