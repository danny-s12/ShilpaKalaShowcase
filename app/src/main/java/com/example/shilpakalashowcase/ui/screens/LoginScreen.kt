package com.example.shilpakalashowcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLogin by remember { mutableStateOf(true) }

    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text =
                if (isLogin)
                    "Welcome to Shilpa-Kala"
                else
                    "Create Account",

            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Name Field (Signup only)
        if (!isLogin) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login / Signup Button
        Button(
            onClick = {

                // Validation
                if (email.isEmpty() || password.isEmpty()) {

                    Toast.makeText(
                        navController.context,
                        "Email and Password cannot be empty",
                        Toast.LENGTH_LONG
                    ).show()

                    return@Button
                }

                if (!isLogin && name.isEmpty()) {

                    Toast.makeText(
                        navController.context,
                        "Please enter your name",
                        Toast.LENGTH_LONG
                    ).show()

                    return@Button
                }

                if (password.length < 6) {

                    Toast.makeText(
                        navController.context,
                        "Password must be at least 6 characters",
                        Toast.LENGTH_LONG
                    ).show()

                    return@Button
                }

                // LOGIN
                if (isLogin) {

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                val userName =
                                    auth.currentUser?.displayName ?: "User"

                                Toast.makeText(
                                    navController.context,
                                    "Login Successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                viewModel.login(userName)

                                navController.navigate("dashboard") {
                                    popUpTo("login") { inclusive = true }
                                }

                            } else {

                                Toast.makeText(
                                    navController.context,
                                    task.exception?.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                } else {

                    // SIGNUP
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                val profileUpdates =
                                    UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build()

                                auth.currentUser
                                    ?.updateProfile(profileUpdates)

                                Toast.makeText(
                                    navController.context,
                                    "Signup Successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                viewModel.login(name)

                                navController.navigate("dashboard") {
                                    popUpTo("login") { inclusive = true }
                                }

                            } else {

                                Toast.makeText(
                                    navController.context,
                                    task.exception?.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (isLogin)
                        "Login"
                    else
                        "Sign Up"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Toggle Login / Signup
        TextButton(
            onClick = {
                isLogin = !isLogin
            }
        ) {

            Text(
                text =
                    if (isLogin)
                        "Don't have an account? Sign Up"
                    else
                        "Already have an account? Login"
            )
        }
    }
}