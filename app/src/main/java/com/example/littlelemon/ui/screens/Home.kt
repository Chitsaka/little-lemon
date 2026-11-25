package com.example.littlelemon.ui.screens

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

@Composable
fun HomeScreen(onProfileClick: () -> Unit) {
    Button(onClick = onProfileClick) {
        Text("Go to Profile")
    }
}
