package com.example.shilpakalashowcase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Register")
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text("Email")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Password")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    // Registration logic here

                    navController.navigate("login")

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Register")
            }
        }
    }
}