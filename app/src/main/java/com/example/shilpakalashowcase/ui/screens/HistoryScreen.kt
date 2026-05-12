package com.example.shilpakalashowcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.MainViewModel
import com.example.shilpakalashowcase.OrderHistory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController, viewModel: MainViewModel) {

    val history by viewModel.orderHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inquiry History", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        if (history.isEmpty()) {
            // ── Empty state ──────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.History,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "No inquiries yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        "Tap the → button on any artwork to enquire via WhatsApp",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }
            }
        } else {
            // ── History list ─────────────────────────────────────────────
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(4.dp)) }

                items(history) { item ->
                    HistoryCard(item)
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun HistoryCard(item: OrderHistory) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ── Status icon ──
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        if (item.status == "Completed")
                            Color(0xFF6F4CC3).copy(alpha = 0.15f)
                        else
                            MaterialTheme.colorScheme.secondaryContainer,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (item.status == "Completed")
                        Icons.Default.CheckCircle else Icons.Default.Send,
                    contentDescription = null,
                    tint = if (item.status == "Completed")
                        Color(0xFF6F4CC3) else MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // ── Text info ──
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.artworkTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "by ${item.artistName}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (item.status == "Completed")
                            Color(0xFF6F4CC3).copy(alpha = 0.12f)
                        else
                            MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = item.status,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (item.status == "Completed")
                                Color(0xFF6F4CC3) else MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.timestamp,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            // ── Price ──
            Text(
                text = "$${item.price.toInt()}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
