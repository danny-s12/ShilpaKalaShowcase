package com.example.shilpakalashowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shilpakalashowcase.ui.screens.Artist
import com.example.shilpakalashowcase.ui.screens.ArtistDetailScreen
import com.example.shilpakalashowcase.ui.screens.DashboardScreen
import com.example.shilpakalashowcase.ui.screens.HeritageStoryScreen
import com.example.shilpakalashowcase.ui.screens.HistoryScreen
import com.example.shilpakalashowcase.ui.screens.LoginScreen
import com.example.shilpakalashowcase.ui.screens.ProfileScreen
import com.example.shilpakalashowcase.ui.screens.RegisterScreen
import com.example.shilpakalashowcase.ui.screens.SettingsScreen
import com.example.shilpakalashowcase.ui.screens.TimelineScreen
import com.example.shilpakalashowcase.ui.theme.ShilpaKalaShowcaseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            ShilpaKalaShowcaseTheme {

                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {

            // LOGIN SCREEN
            composable("login") {

                LoginScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // REGISTER SCREEN
            composable("register") {

                RegisterScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // DASHBOARD SCREEN
            composable("dashboard") {

                DashboardScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // PROFILE SCREEN
            composable("profile") {

                ProfileScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // SETTINGS SCREEN
            composable("settings") {

                SettingsScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // HISTORY SCREEN
            composable("history") {

                HistoryScreen(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }

            // TIMELINE SCREEN
            composable("timeline") {

                TimelineScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // HERITAGE STORY SCREEN
            composable("heritage_story") {

                HeritageStoryScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // ARTIST DETAIL SCREEN
            composable("artist_detail") {

                val sampleArtist = Artist(
                    name = "Shilpa",
                    imageUrl = "https://images.unsplash.com/photo-1618220048045-10a6db86b6a2?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80",
                    specialization = "Traditional Sculpture",
                    biography = "Experienced artist specializing in handmade artworks and cultural sculptures.",
                    artworks = listOf(
                        "Stone Sculpture",
                        "Wood Carving",
                        "Clay Modeling"
                    )
                )

                ArtistDetailScreen(
                    artist = sampleArtist,

                    onBackClick = {
                        navController.popBackStack()
                    },

                    onTimelineClick = {
                        navController.navigate("timeline")
                    }
                )
            }
        }
    }
}