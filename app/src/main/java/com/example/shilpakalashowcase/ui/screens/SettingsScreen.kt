package com.example.shilpakalashowcase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.AppSettings
import com.example.shilpakalashowcase.MainViewModel
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val user by viewModel.currentUser.collectAsState()
    val settings by viewModel.settings.collectAsState()

    // Local copies of toggle states
    var notificationsEnabled by remember(settings) { mutableStateOf(settings.notificationsEnabled) }
    var selectedLanguage by remember(settings) { mutableStateOf(settings.language) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Language options
    val languages = listOf("English", "Kannada", "Hindi", "Tamil")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // ── Account Section ──────────────────────────────────────────
            SettingsSectionTitle("Account")

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                Column {
                    // User info row
                    ListItem(
                        headlineContent = {
                            Text(user?.name ?: "Guest", fontWeight = FontWeight.SemiBold)
                        },
                        supportingContent = {
                            Text(user?.role?.name ?: "", color = MaterialTheme.colorScheme.primary)
                        },
                        leadingContent = {
                            Icon(Icons.Default.AccountCircle, null, modifier = Modifier.size(40.dp))
                        },
                        trailingContent = {
                            TextButton(onClick = { navController.navigate("profile") }) {
                                Text("Edit")
                            }
                        }
                    )
                    HorizontalDivider()
                    // Artist Portfolio row
                    ListItem(
                        headlineContent = { Text("Artist Portfolio") },
                        supportingContent = { Text("View your artist profile page") },
                        leadingContent = {
                            Icon(Icons.Default.Person, null, tint = MaterialTheme.colorScheme.primary)
                        },
                        trailingContent = {
                            Icon(Icons.Default.ChevronRight, null)
                        },
                        modifier = Modifier.clickableNoRipple { navController.navigate("artist_detail") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Notifications Section ────────────────────────────────────
            SettingsSectionTitle("Notifications")

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                ListItem(
                    headlineContent = { Text("Push Notifications") },
                    supportingContent = { Text("Order updates and new artwork alerts") },
                    leadingContent = {
                        Icon(Icons.Default.Notifications, null, tint = MaterialTheme.colorScheme.primary)
                    },
                    trailingContent = {
                        Switch(
                            checked = notificationsEnabled,
                            onCheckedChange = {
                                notificationsEnabled = it
                                viewModel.updateSettings(
                                    AppSettings(notificationsEnabled = it, language = selectedLanguage)
                                )
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Explore Section ──────────────────────────────────────────
            SettingsSectionTitle("Explore")

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("Heritage Stories") },
                        supportingContent = { Text("Learn about traditional carving styles") },
                        leadingContent = {
                            Icon(Icons.Default.AutoStories, null, tint = MaterialTheme.colorScheme.primary)
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, null) },
                        modifier = Modifier.clickableNoRipple { navController.navigate("heritage_story") }
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Work Timeline") },
                        supportingContent = { Text("Stone to statue progress tracker") },
                        leadingContent = {
                            Icon(Icons.Default.Timeline, null, tint = MaterialTheme.colorScheme.primary)
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, null) },
                        modifier = Modifier.clickableNoRipple { navController.navigate("timeline") }
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Order History") },
                        supportingContent = { Text("View your past inquiries") },
                        leadingContent = {
                            Icon(Icons.Default.History, null, tint = MaterialTheme.colorScheme.primary)
                        },
                        trailingContent = { Icon(Icons.Default.ChevronRight, null) },
                        modifier = Modifier.clickableNoRipple { navController.navigate("history") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── Language Section ─────────────────────────────────────────
            SettingsSectionTitle("Language")

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                ListItem(
                    headlineContent = { Text("App Language") },
                    supportingContent = { Text(selectedLanguage) },
                    leadingContent = {
                        Icon(Icons.Default.Language, null, tint = MaterialTheme.colorScheme.primary)
                    },
                    trailingContent = { Icon(Icons.Default.ChevronRight, null) },
                    modifier = Modifier.clickableNoRipple { showLanguageDialog = true }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── App Info ─────────────────────────────────────────────────
            SettingsSectionTitle("About")

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("App Version") },
                        supportingContent = { Text("Shilpa-Kala Showcase v1.0") },
                        leadingContent = {
                            Icon(Icons.Default.Info, null, tint = MaterialTheme.colorScheme.primary)
                        }
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("MindMatrix VTU Project #99") },
                        supportingContent = { Text("National Pride — Android + GenAI") },
                        leadingContent = {
                            Icon(Icons.Default.School, null, tint = MaterialTheme.colorScheme.primary)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Logout Button ────────────────────────────────────────────
            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(Icons.Default.Logout, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Logout", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // ── Language Dialog ──────────────────────────────────────────────
        if (showLanguageDialog) {
            AlertDialog(
                onDismissRequest = { showLanguageDialog = false },
                title = { Text("Select Language") },
                text = {
                    Column {
                        languages.forEach { lang ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickableNoRipple {
                                        selectedLanguage = lang
                                        viewModel.updateSettings(
                                            AppSettings(
                                                notificationsEnabled = notificationsEnabled,
                                                language = lang
                                            )
                                        )
                                        showLanguageDialog = false
                                    }
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedLanguage == lang,
                                    onClick = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(lang)
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showLanguageDialog = false }) {
                        Text("Close")
                    }
                }
            )
        }

        // ── Logout Confirm Dialog ────────────────────────────────────────
        if (showLogoutDialog) {
            AlertDialog(
                onDismissRequest = { showLogoutDialog = false },
                title = { Text("Logout") },
                text = { Text("Are you sure you want to logout?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showLogoutDialog = false
                            viewModel.logout()
                            navController.navigate("login") { popUpTo(0) }
                        }
                    ) { Text("Yes, Logout", color = MaterialTheme.colorScheme.error) }
                },
                dismissButton = {
                    TextButton(onClick = { showLogoutDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}

@Composable
private fun SettingsSectionTitle(title: String) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
    )
}

// Helper: clickable without ripple for ListItem
@Composable
private fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier =
    this.clickable(onClick = onClick)