package com.example.docx.domain.model

// Please replace with your desired data model

data class Appointment(
    val start: Long,
    val end: Long,
    val appointedBy: String,
    val isBooked: Boolean,
    val illness: String? // since watching time is only 30 mins, it is a good idea to what the patient is suffering from, also making it OPTIONAL
)

// appointedBy is not as same as bookedBy,
// if someone book then isBooked is true and in the CARD composable appointed by text changes to booked by