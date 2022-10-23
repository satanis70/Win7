package com.example.win7

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.win7.api.SportsDataApi
import com.example.win7.model.PopularFighter
import com.example.win7.model.Termin
import com.example.win7.ui.theme.MyGray
import com.example.win7.ui.theme.Win7Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

var arrayListTermins = ArrayList<Termin>()
var terminsListResponse: List<Termin> by mutableStateOf(listOf())


class TerminsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetData()
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(MyGray)
            ) {
                itemsIndexed(terminsListResponse) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color.White)
                                .padding(10.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = item.name,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 14.dp)
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = item.description,
                                    fontSize = 18.sp,
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

private fun GetData() {
    val api = Retrofit.Builder()
        .baseUrl("http://49.12.202.175/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SportsDataApi::class.java)
    GlobalScope.launch {
        val response = api.getTermins().awaitResponse()
        launch(Dispatchers.Main) {
            terminsListResponse = response.body()!!.termins
            arrayListTermins = terminsListResponse as ArrayList<Termin>
        }
    }
}

