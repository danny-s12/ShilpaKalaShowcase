package com.example.shilpakalashowcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// ──────────────────────────────────────────────────────────────────────────────
// Model
// ──────────────────────────────────────────────────────────────────────────────
data class TimelineStep(
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: String,
    val completed: Boolean,
    val icon: ImageVector
)

// ──────────────────────────────────────────────────────────────────────────────
// Data — "Stone Buddha" in-progress example
// ──────────────────────────────────────────────────────────────────────────────
private val buddhaTimeline = listOf(
    TimelineStep(
        title = "Raw Stone Selected",
        description = "A 40 kg block of Hoysala soapstone hand-picked from the quarry at Shivarapatna.",
        imageUrl = "https://picsum.photos/seed/t1/600/350",
        date = "Jan 10, 2025",
        completed = true,
        icon = Icons.Default.Inventory
    ),
    TimelineStep(
        title = "Rough Shaping",
        description = "Chisel work begins — rough outline of the seated Buddha figure emerges from the block.",
        imageUrl = "https://picsum.photos/seed/t2/600/350",
        date = "Jan 18, 2025",
        completed = true,
        icon = Icons.Default.Build
    ),
    TimelineStep(
        title = "Face & Mudra Carving",
        description = "Detailed face, serene expression and dhyana mudra (meditation hand gesture) carved with fine chisels.",
        imageUrl = "https://picsum.photos/seed/t3/600/350",
        date = "Feb 5, 2025",
        completed = true,
        icon = Icons.Default.AutoFixHigh
    ),
    TimelineStep(
        title = "Robe & Lotus Detailing",
        description = "Flowing robe drapery and lotus throne petals carved with precision — the most time-intensive step.",
        imageUrl = "https://picsum.photos/seed/t4/600/350",
        date = "Feb 28, 2025",
        completed = false,
        icon = Icons.Default.Brush
    ),
    TimelineStep(
        title = "Polishing & Finishing",
        description = "Surface polished using progressively finer sandpaper grades. Natural oil applied for lustre.",
        imageUrl = "https://picsum.photos/seed/t5/600/350",
        date = "Mar 15, 2025",
        completed = false,
        icon = Icons.Default.Star
    ),
    TimelineStep(
        title = "Quality Check & Dispatch",
        description = "Final inspection, protective packaging, and ready for delivery to the buyer.",
        imageUrl = "https://picsum.photos/seed/t6/600/350",
        date = "Mar 22, 2025",
        completed = false,
        icon = Icons.Default.CheckCircle
    )
)

// ──────────────────────────────────────────────────────────────────────────────
// Screen
// ──────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(onBackClick: () -> Unit) {

    val completedCount = buddhaTimeline.count { it.completed }
    val progress = completedCount.toFloat() / buddhaTimeline.size.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Work-in-Progress Timeline", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Artwork Header ─────────────────────────────────────────
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Stone Buddha",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "by Rakshitha · Product ID: SKS-001",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$completedCount / ${buddhaTimeline.size} stages done",
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "${(progress * 100).toInt()}%",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // ── Timeline Steps ─────────────────────────────────────────
            itemsIndexed(buddhaTimeline) { index, step ->
                TimelineStepItem(
                    step = step,
                    index = index,
                    isLast = index == buddhaTimeline.lastIndex
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

// ──────────────────────────────────────────────────────────────────────────────
// Individual Timeline Step
// ──────────────────────────────────────────────────────────────────────────────
@Composable
fun TimelineStepItem(step: TimelineStep, index: Int, isLast: Boolean) {
    val completedColor = Color(0xFF6F4CC3)
    val pendingColor = MaterialTheme.colorScheme.outlineVariant

    Row(modifier = Modifier.fillMaxWidth()) {

        // ── Left column: circle + connector line ──
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        if (step.completed) completedColor else MaterialTheme.colorScheme.surfaceVariant,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = step.icon,
                    contentDescription = null,
                    tint = if (step.completed) Color.White else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(22.dp)
                )
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(if (step.completed) 200.dp else 160.dp)
                        .background(if (step.completed) completedColor.copy(alpha = 0.4f) else pendingColor)
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        // ── Right column: card content ──
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else 16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (step.completed)
                        MaterialTheme.colorScheme.surfaceVariant
                    else
                        MaterialTheme.colorScheme.surface
                ),
                border = if (!step.completed) CardDefaults.outlinedCardBorder() else null
            ) {
                Column {
                    // Photo of this step
                    AsyncImage(
                        model = step.imageUrl,
                        contentDescription = step.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = step.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                            // Status chip
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (step.completed)
                                    completedColor.copy(alpha = 0.12f)
                                else
                                    MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
                            ) {
                                Text(
                                    text = if (step.completed) "Done" else "Pending",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (step.completed) completedColor else MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = step.description,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "📅 ${step.date}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}
