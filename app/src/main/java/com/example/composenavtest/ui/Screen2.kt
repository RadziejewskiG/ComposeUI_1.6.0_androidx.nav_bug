package com.example.composenavtest.ui

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class Screen2 : ComposableFragment() {

    @Composable
    override fun Content(args: Bundle?) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SCREEN 2",
                color = Color.White
            )
        }
    }
}
