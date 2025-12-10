package com.example.docx.presentation.appointment_history_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.docx.domain.model.Appointment
import com.example.docx.utils.toPrettyDate
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentHistoryDetailsScreen(
    modifier: Modifier = Modifier,
    appointmentHistoryId: Int
) {
    val appointment = Appointment(
        start = Instant.now().toEpochMilli(),
        end = Instant.now().toEpochMilli(),
        appointedBy = "Lorem",
        illness = "Pagal"
    )

    val prescription = "Take this drug" // todo for now I am not using appointment history since I cannot simulate db query

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = appointment.appointedBy,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        // I am using Lazy column instead of column because the prescription may be longer and may over flow
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            // TODO: JUST TO TEST IF NAVIGATION WORKING, REMOVE IT ON PROD
            item {
                Text(
                    text = "$appointmentHistoryId"
                )
            }
            item {
                Text(
                    text = appointment.appointedOn.toPrettyDate()
                )
            }

            item {
                Text(
                    text = "Reason: ${appointment.illness}"
                )
            }

            item {
                Text(
                    text = "Prescription: ",
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Text(
                    text = prescription
                )
            }
        }
    }
}





























