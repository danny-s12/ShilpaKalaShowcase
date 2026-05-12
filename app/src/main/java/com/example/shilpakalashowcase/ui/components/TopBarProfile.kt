package com.example.shilpakalashowcase.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.MainViewModel

@Composable
fun TopBarProfile(
    navController: NavController,
    viewModel: MainViewModel
) {

    val user by viewModel.currentUser.collectAsState()

    var expanded by remember {
        mutableStateOf(false)
    }

    // USER NOT LOGGED IN
    if (user?.uid.isNullOrEmpty()) {

        Text(
            text = "Login",
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {
                    navController.navigate("login")
                }
        )

    } else {

        // USER LOGGED IN
        Row(
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {
                    expanded = true
                }
        ) {

            Text(text = user?.name ?: "Profile")

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            DropdownMenuItem(
                text = { Text("Profile") },
                onClick = {
                    expanded = false
                    navController.navigate("profile")
                }
            )

            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {
                    expanded = false
                    navController.navigate("settings")
                }
            )

            DropdownMenuItem(
                text = { Text("Logout") },
                onClick = {
                    expanded = false

                    viewModel.logout()

                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}