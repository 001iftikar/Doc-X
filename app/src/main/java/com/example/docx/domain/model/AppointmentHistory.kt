package com.example.docx.domain.model

import kotlin.random.Random

data class AppointmentHistory(
    // primary key
    val id: Int = Random.nextInt(),
    val appointment: Int, // foreign key
    val prescription: String // describe the prescription for the specific appointment id
    // and mark as seen the patient and save in db
)
