package com.example.docx.presentation.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.docx.R
import com.example.docx.ui.theme.GradientBackground

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen() {
    val doctorUsername = "doctor_x"
    /*
    Doc's name, profile picture, experience(if given), specializations will be shown to the patients
     */
    val doctorName = "Doctor X" // todo change hardcoded name
    val profilePicture = R.drawable.doctor_placeholder // todo also make this dynamic
    val contact = 773628346 // todo hard codded
    val location = "Seoul, South Korean"
    // an optional experience
    val experience: Int? = 12 // if the doctor sets an experience it will shown otherwise NO
    val dummyList =
        remember { mutableStateListOf<String>("Must have at least one Specialization") } // retrieve from db
    val qualification = "MBBS- AIIMS" // todo replace hard codded
    var isPopUpOpen by remember { mutableStateOf(false) }

    val onEditClick = {
        isPopUpOpen = true
    }

    val onPopUpDismiss = {
        isPopUpOpen = false
    }

    val onConfirm = {
        // todo first save the specialiations
        isPopUpOpen = false
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(doctorUsername) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileCard(
                doctorName = doctorName,
                contact = contact,
                location = location,
                experience = experience,
                profilePic = profilePicture
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "QUALIFICATIONS",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = qualification,
                            fontSize = 14.sp
                        )
                    }
                }
                item {
                    Specializations(onEditClick)
                }

                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        dummyList.forEach { specialization ->
                            SpecsChip(specialization)
                        }
                    }
                }
            }
        }
        EditSpecializationPopUp(
            isOpen = isPopUpOpen,
            onDismiss = onPopUpDismiss,
            onConfirm = { spec ->
                val parsedList = spec
                    .split(",")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                dummyList.addAll(parsedList)
                onConfirm()
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) }
    )
}

@Composable
private fun ProfileCard(
    doctorName: String,
    contact: Int,
    location: String,
    experience: Int?,
    profilePic: Any
) {
    ElevatedCard(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .background(brush = Brush.horizontalGradient(colors = GradientBackground))
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = profilePic,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = doctorName,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            experience?.let {
                Text(
                    text = "Experience: $it years",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Text(
                    text = location,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Text(
                    text = contact.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun Specializations(
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "SPECIALIZATIONS",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )


        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit specializations",
            modifier = Modifier
                .size(16.dp)
                .clickable(
                    onClick = onClick
                )
        )
    }
}

@Composable
private fun SpecsChip(
    spec: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF046322)
        )
    ) {
        Text(
            text = spec,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun EditSpecializationPopUp(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var specializations by remember { mutableStateOf("") }
    if (isOpen) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    "Please add comma separated",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            text = {
                OutlinedTextField(
                    value = specializations,
                    onValueChange = { specializations = it }
                )
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm(specializations) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green
                    )
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}


































