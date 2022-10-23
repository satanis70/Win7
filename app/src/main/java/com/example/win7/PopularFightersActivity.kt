package com.example.win7

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.win7.api.SportsDataApi
import com.example.win7.model.PopularFighter
import com.example.win7.ui.theme.MyGray
import com.example.win7.ui.theme.Win7Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

var arrayList = ArrayList<PopularFighter>()
var fightersListResponse: List<PopularFighter> by mutableStateOf(listOf())

class PopularFightersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetData()
            val uriHandler = LocalUriHandler.current
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(MyGray)
            ) {
                itemsIndexed(fightersListResponse) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(150.dp)
                                    .clip(CircleShape),
                                painter = rememberAsyncImagePainter(model = item.image),
                                contentScale = ContentScale.Crop, contentDescription = ""
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = item.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = item.nickname, fontSize = 18.sp)
                                Text(text = item.dateofbirth, fontSize = 18.sp)
                                Text(text = item.weightclass, fontSize = 18.sp)
                                Text(text = item.proMMARecord, fontSize = 18.sp)
                                Text(
                                    text = item.careerdisclosedearnings,
                                    fontSize = 18.sp,
                                    color = Color.Green
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.insta),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clickable {
                                                uriHandler.openUri(item.link)
                                            }
                                    )
                                }

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
        val response = api.getFighters().awaitResponse()
        launch(Dispatchers.Main) {
            fightersListResponse = response.body()!!.popularFighters
            responseResult(fightersListResponse as ArrayList<PopularFighter>)
        }
    }

}

private fun responseResult(fightersListResponse: ArrayList<PopularFighter>) {
    Log.d("DSGFHFHD", fightersListResponse.toString())
    arrayList = fightersListResponse
}

