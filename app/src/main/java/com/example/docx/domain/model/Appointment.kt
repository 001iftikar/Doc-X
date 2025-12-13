package com.example.docx.domain.model

import java.time.Instant
import kotlin.random.Random

// Please replace with your desired data model

data class Appointment(
    // primary key
    val id: Int = Random.nextInt(), // in prod keep it uninitialized
    val start: Long,
    val end: Long,
    val appointedOn: Long = Instant.now().toEpochMilli(), // the date of appointment
    val appointedBy: String?,
    val patientId: Int = Random.nextInt(), // foreign key reference todo replace the hardcoding
    val isBooked: Boolean = false, // will not booked when created
    val illness: String?, // since watching time is only 30 mins, it is a good idea to what the patient is suffering from, also making it OPTIONAL
    val isSeen: Boolean = false // when the appointment gets created isSeen is false, then the doctor mark the patient as seen after seeing the patient
)

// appointedBy is not as same as bookedBy,
// if someone book then isBooked is true and in the CARD composable appointed by text changes to booked by