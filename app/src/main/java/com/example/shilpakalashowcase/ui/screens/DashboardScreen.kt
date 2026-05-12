package com.example.shilpakalashowcase.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shilpakalashowcase.MainViewModel
import com.example.shilpakalashowcase.R
import com.example.shilpakalashowcase.data.Artwork
import com.example.shilpakalashowcase.ui.components.TopBarProfile

// ─────────────────────────────────────────────────────────────────────────────
// Helper: maps drawable name → R.drawable ID at runtime
// ─────────────────────────────────────────────────────────────────────────────
fun getDrawableId(context: android.content.Context, name: String): Int {
    val id = context.resources.getIdentifier(name, "drawable", context.packageName)
    return if (id != 0) id else R.drawable.ic_launcher_background
}

// ─────────────────────────────────────────────────────────────────────────────
// 22 Artworks — imageUrl stores the drawable filename
// ─────────────────────────────────────────────────────────────────────────────
val sampleArtworks = listOf(
    Artwork("SKS-001","A1","Rakshitha","Stone Buddha",        "Hand-carved meditating Buddha in Hoysala soapstone",          "img_stone_buddha",      "Stone","Hoysala",     1200.0),
    Artwork("SKS-002","A1","Rakshitha","Wooden Elephant",     "Traditional Mysore-style carved wooden elephant",             "img_wooden_elephant",   "Wood", "Chola",       450.0),
    Artwork("SKS-003","A2","Priya",    "Marble Goddess",      "Fine-detail Durga sculpture in white marble",                 "img_marble_goddess",    "Stone","Gandhara",    2500.0),
    Artwork("SKS-004","A2","Priya",    "Brass Nandi",         "Temple-style Nandi bull in Vijayanagara tradition",           "img_brass_nandi",       "Metal","Vijayanagara",3200.0),
    Artwork("SKS-005","A3","Suresh",   "Dancing Shiva",       "Nataraja — Lord of Dance in Chola bronze style",             "img_dancing_shiva",     "Metal","Chola",       4800.0),
    Artwork("SKS-006","A3","Suresh",   "Teak Ganesha",        "Intricately carved teak wood Ganesha idol",                  "img_teak_ganesha",      "Wood", "Hoysala",      780.0),
    Artwork("SKS-007","A4","Meena",    "Sandstone Pillar",    "Miniature carved temple pillar in Pallava style",            "img_sandstone_pillar",  "Stone","Pallava",     1900.0),
    Artwork("SKS-008","A4","Meena",    "Rosewood Krishna",    "Flute-playing Krishna carved in rosewood",                   "img_rosewood_krishna",  "Wood", "Mysore",       650.0),
    Artwork("SKS-009","A5","Ravi",     "Bronze Lakshmi",      "Seated Lakshmi with lotuses in Chola bronze",               "img_bronze_lakshmi",    "Metal","Chola",       5500.0),
    Artwork("SKS-010","A5","Ravi",     "Black Stone Yali",    "Mythical lion-creature guardian in Vijayanagara style",      "img_black_yali",        "Stone","Vijayanagara",2100.0),
    Artwork("SKS-011","A1","Rakshitha","Stone Saraswati",     "Veena-playing Saraswati in Hoysala soapstone",              "img_stone_saraswati",   "Stone","Hoysala",     3400.0),
    Artwork("SKS-012","A2","Priya",    "Walnut Wood Vishnu",  "Standing four-armed Vishnu carved in walnut wood",          "img_walnut_vishnu",     "Wood", "Chola",        890.0),
    Artwork("SKS-013","A3","Suresh",   "Copper Murugan",      "Vel-holding Lord Murugan in Pallava tradition",             "img_copper_murugan",    "Metal","Pallava",     2900.0),
    Artwork("SKS-014","A4","Meena",    "Granite Hanuman",     "Warrior-pose Hanuman carved in black granite",              "img_granite_hanuman",   "Stone","Vijayanagara",1750.0),
    Artwork("SKS-015","A5","Ravi",     "Ebony Dancing Figure","Apsara dancing court lady in ebony wood",                   "img_ebony_dancing",     "Wood", "Mysore",      1100.0),
    Artwork("SKS-016","A1","Rakshitha","Soapstone Elephant Pair","Decorative elephant frieze in Hoysala soapstone",        "img_elephant_pair",     "Stone","Hoysala",      560.0),
    Artwork("SKS-017","A2","Priya",    "Brass Deepa Lakshmi", "Traditional oil lamp holder deity in Chola brass",          "img_deepa_lakshmi",     "Metal","Chola",       1350.0),
    Artwork("SKS-018","A3","Suresh",   "Mango Wood Panel",    "Floral lattice decorative panel in mango wood",             "img_wood_panel",        "Wood", "Kerala",       420.0),
    Artwork("SKS-019","A4","Meena",    "Limestone Frieze",    "Temple wall bas-relief panel in Pallava limestone",         "img_limestone_frieze",  "Stone","Pallava",     2200.0),
    Artwork("SKS-020","A5","Ravi",     "Panchaloha Balakrishna","Five-metal crawling baby Krishna in Chola style",         "img_balakrishna",       "Metal","Chola",       7800.0),
    Artwork("SKS-021","A1","Rakshitha","Teak Temple Chariot", "Miniature intricately carved temple chariot in teak",       "img_teak_chariot",      "Wood", "Vijayanagara",3100.0),
    Artwork("SKS-022","A2","Priya",    "Black Granite Shiva Linga","Shiva Linga with Nandi and yoni base in black granite","img_shiva_linga",       "Stone","Hoysala",     4200.0),
)

// ─────────────────────────────────────────────────────────────────────────────
// Dashboard Screen
// ─────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    var selectedCategory by remember { mutableStateOf("All") }

    val filteredArtworks = remember(selectedCategory) {
        if (selectedCategory == "All") sampleArtworks
        else sampleArtworks.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "SHILPA-KALA",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Traditional Excellence",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                actions = {
                    TopBarProfile(navController = navController, viewModel = viewModel)
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.History, contentDescription = "History") },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("settings") },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            androidx.compose.foundation.lazy.LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    CategoryFilterRow(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it }
                    )
                }

                item {
                    HeritageStoryBanner(onClick = { navController.navigate("heritage_story") })
                }

                item {
                    Text(
                        text = "Featured Sculptures (${filteredArtworks.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                items(filteredArtworks.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { artwork ->
                            Box(modifier = Modifier.weight(1f)) {
                                ArtworkCard(
                                    artwork = artwork,
                                    onClick = { navController.navigate("artist_detail") }
                                )
                            }
                        }
                        if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Heritage Story Banner
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun HeritageStoryBanner(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.AutoStories, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Heritage Stories", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall)
                Text("Explore Hoysala, Chola & Vijayanagara carving traditions",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Default.ArrowForward, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Category Filter Row
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun CategoryFilterRow(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    val categories = listOf("All", "Stone", "Wood", "Metal")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category) }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Artwork Card — uses LOCAL drawable image (no internet needed)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun ArtworkCard(artwork: Artwork, onClick: () -> Unit = {}) {
    val context = LocalContext.current
    val drawableId = getDrawableId(context, artwork.imageUrl)

    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = artwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = artwork.artistName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = artwork.style,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${artwork.price.toInt()}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            val message =
                                "Hi, I am interested in '${artwork.title}' " +
                                        "(Product ID: ${artwork.id}) by ${artwork.artistName}. " +
                                        "Price: \$${artwork.price.toInt()}. Please share availability."
                            val uri = Uri.parse(
                                "https://api.whatsapp.com/send?phone=9682397579&text=${Uri.encode(message)}"
                            )
                            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Enquire via WhatsApp",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
