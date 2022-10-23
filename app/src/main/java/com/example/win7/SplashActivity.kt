package com.example.win7

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.win7.ui.theme.MyGray
import kotlinx.coroutines.delay
import kotlin.system.exitProcess


var isDisplayed: Boolean by mutableStateOf(true)
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MyGray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "MMA INFO", color = Color.White, fontSize = 36.sp,
                style = MaterialTheme.typography.h1)
                if (isDisplayed){
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp),
                        color = Color.Green,
                        strokeWidth = Dp(4f)
                    )
                }

            }
            LaunchedEffect(key1 = true) {
                delay(3000L)
                isDisplayed = !isDisplayed
                val activity = SplashActivity()
                startActivity(Intent(context, MainActivity::class.java))
                activity.finish()
                exitProcess(0)
            }

        }
    }
}

