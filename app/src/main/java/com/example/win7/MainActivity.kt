package com.example.win7

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.win7.api.SportsDataApi
import com.example.win7.model.PopularFighter
import com.example.win7.ui.theme.MyGray
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
            OneSignal.initWithContext(context)
            OneSignal.setAppId("714b9f14-381d-4fc4-a93c-28d480557381")
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MyGray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(bottom = 84.dp)) {
                    Text(text = "MMA INFO", color = Color.White, fontSize = 36.sp,
                        style = MaterialTheme.typography.h1)
                }
                Button(
                    onClick = {
                        startActivity(Intent(context, PopularFightersActivity::class.java))
                    },
                ) {
                    Text(text = "Most popular fighters")
                }
                Button(onClick = {
                    startActivity(Intent(context, TerminsActivity::class.java))
                }) {
                    Text(text = "MMA termins")
                }
                Button(onClick = {
                    startActivity(Intent(context, OrganizationsActivity::class.java))
                }) {
                    Text(text = "Top 10 Best MMA Organizations")
                }
            }
            BackHandler(enabled = true, onBack = {
                val activity = MainActivity()
                activity.finish()
                exitProcess(0)
            })
        }
    }


}






