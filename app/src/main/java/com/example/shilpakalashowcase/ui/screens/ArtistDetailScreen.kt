package com.example.shilpakalashowcase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.res.painterResource
import com.example.shilpakalashowcase.R


import androidx.compose.ui.graphics.Color

data class Artist(
    val name: String,
    val imageUrl: String,
    val specialization: String,
    val biography: String,
    val artworks: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(
    artist: Artist,
    onBackClick: () -> Unit,
    onTimelineClick: () -> Unit // Added navigation parameter here
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Artist Portfolio")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // --- Artist Info Section ---
            item {
//                Image(
//                    painter = rememberAsyncImagePainter(artist.imageUrl),
//                    contentDescription = artist.name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(250.dp)
//                        .clip(RoundedCornerShape(16.dp)),
//                    contentScale = ContentScale.Crop
//                )

//                Image(
//                    painter = rememberAsyncImagePainter(artist.imageUrl),
//                    contentDescription = artist.name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(250.dp)
//                        .clip(RoundedCornerShape(16.dp)),
//                    contentScale = ContentScale.Crop
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    // R.drawable.artist_profile matches the filename you saved in the drawable folder
                    painter = painterResource(id = R.drawable.artist_profile),
                    contentDescription = "Profile picture of ${artist.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = artist.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = artist.specialization,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Biography",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = artist.biography,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Artworks",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            // --- Artworks List Section ---
            items(artist.artworks.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = artist.artworks[index],
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // --- Timeline Button Section (NEW) ---
            item {
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onTimelineClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp), // slightly taller for a better touch target
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6F4CC3) // Matching your timeline purple
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "View Work Timeline",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }

                // Add bottom padding so the button doesn't hug the screen edge
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}