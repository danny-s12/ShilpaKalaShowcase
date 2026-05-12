package com.example.shilpakalashowcase.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// ──────────────────────────────────────────────────────────────────────────────
// Data model for a carving tradition entry
// ──────────────────────────────────────────────────────────────────────────────
data class HeritageTradition(
    val style: String,
    val period: String,
    val region: String,
    val imageUrl: String,
    val summary: String,
    val keyFeatures: List<String>
)

val heritageList = listOf(

    HeritageTradition(
        style = "Hoysala",
        period = "11th – 14th Century CE",
        region = "Karnataka (Belur, Halebidu, Somnathapura)",
        imageUrl = "https://picsum.photos/seed/hoysala/800/400",
        summary = "The Hoysala tradition is renowned for the intricate soapstone " +
                "(chloritic schist) carvings covering every inch of temple walls. " +
                "The sculptors worked with extreme precision, producing lathe-turned " +
                "pillars, miniature friezes, and over 240 deities per temple. " +
                "The star-shaped platform (stellate plan) is unique to Hoysala architecture.",

        keyFeatures = listOf(
            "Star-shaped temple platforms",
            "Soapstone (chloritic schist) medium",
            "Miniature friezes and lathe-turned pillars",
            "Rows of elephants, horses, scrolling foliage",
            "Deeply carved, jewellery-like detail"
        )
    ),

    HeritageTradition(
        style = "Chola",
        period = "9th – 13th Century CE",
        region = "Tamil Nadu (Thanjavur, Gangaikonda Cholapuram)",
        imageUrl = "https://picsum.photos/seed/chola/800/400",
        summary = "Chola bronze casting (lost-wax / cire perdue) produced the world's " +
                "finest metal sculptures. The iconic Nataraja — Shiva as Lord of the Dance " +
                "— is a Chola masterpiece still revered globally. Chola sculptors achieved " +
                "fluid movement and spiritual energy in metal, with figures conveying grace, " +
                "divinity, and mathematical proportion.",

        keyFeatures = listOf(
            "Lost-wax (cire perdue) bronze casting",
            "Nataraja as the defining icon",
            "Five-metal alloy (Panchaloha)",
            "Slender proportions, jewelled ornamentation",
            "Dynamic poses with cosmic symbolism"
        )
    ),

    HeritageTradition(
        style = "Vijayanagara",
        period = "14th – 17th Century CE",
        region = "Karnataka / Andhra (Hampi)",
        imageUrl = "https://picsum.photos/seed/vijaya/800/400",
        summary = "The Vijayanagara Empire produced massive monolithic sculptures and ornate " +
                "pillared halls (kalyana mandapas). Hard granite was carved with Yalis " +
                "(mythical lion-horses), intricately sculpted musical pillars that produce " +
                "different notes when tapped, and enormous monolithic Nandi and Ganesha statues.",

        keyFeatures = listOf(
            "Hard granite as primary medium",
            "Yali (mythical creature) pillars",
            "Musical pillars with acoustic properties",
            "Massive monolithic sculptures",
            "Composite pillar capitals"
        )
    ),

    HeritageTradition(
        style = "Pallava",
        period = "6th – 9th Century CE",
        region = "Tamil Nadu (Mahabalipuram, Kanchipuram)",
        imageUrl = "https://picsum.photos/seed/pallava/800/400",
        summary = "The Pallavas pioneered rock-cut temple architecture in South India. " +
                "Mahabalipuram's Shore Temple and the famous Arjuna's Penance panel " +
                "(the world's largest bas-relief) demonstrate Pallava mastery in carving " +
                "living rock into temples, narrative panels, and graceful sculptures " +
                "of deities and celestial beings.",

        keyFeatures = listOf(
            "Rock-cut monolithic rathas (chariots)",
            "World's largest bas-relief panels",
            "Graceful, elongated figure proportions",
            "Shore Temple coastal architecture",
            "Narrative scenes from epics in stone"
        )
    ),

    HeritageTradition(
        style = "Gandhara",
        period = "1st – 5th Century CE",
        region = "Northwest India / Pakistan (Taxila region)",
        imageUrl = "https://picsum.photos/seed/gandh/800/400",
        summary = "Gandhara art represents a unique fusion of Greek, Roman, and Indian " +
                "traditions along the ancient Silk Road. Sculptors produced some of the " +
                "earliest images of the Buddha in human form, with Hellenistic facial " +
                "features, flowing Greco-Roman robes, and Indian spiritual symbolism.",

        keyFeatures = listOf(
            "Greco-Buddhist fusion style",
            "Earliest human depictions of the Buddha",
            "Hellenistic drapery and facial features",
            "Schist stone medium",
            "Narrative Jataka story panels"
        )
    )
)

// ──────────────────────────────────────────────────────────────────────────────
// Heritage Story Screen
// ──────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeritageStoryScreen(
    onBackClick: () -> Unit
) {

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Heritage Stories",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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

            // Intro Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "The Living Legacy of Indian Sculpture",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "For over 2,000 years, Indian artisans have transformed " +
                                "stone, bronze, and wood into divine expressions. " +
                                "Each regional tradition carries a unique language of form, " +
                                "symbol, and spiritual meaning.",

                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Explore Traditions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            heritageList.forEach { tradition ->

                HeritageTraditionCard(
                    tradition = tradition
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ──────────────────────────────────────────────────────────────────────────────
// Expandable Heritage Tradition Card
// ──────────────────────────────────────────────────────────────────────────────
@Composable
fun HeritageTraditionCard(
    tradition: HeritageTradition
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
    ) {

        Column {

            // Hero Image
            AsyncImage(
                model = tradition.imageUrl,
                contentDescription = tradition.style,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    ),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "${tradition.style} Style",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = tradition.period,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = tradition.region,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            expanded = !expanded
                        }
                    ) {

                        Icon(
                            imageVector =
                                if (expanded)
                                    Icons.Default.ExpandLess
                                else
                                    Icons.Default.ExpandMore,

                            contentDescription =
                                if (expanded) "Collapse"
                                else "Expand"
                        )
                    }
                }

                // Expanded Content
                if (expanded) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = tradition.summary,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Key Features",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    tradition.keyFeatures.forEach { feature ->

                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {

                            Text(
                                text = "• ",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = feature,
                                fontSize = 13.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}