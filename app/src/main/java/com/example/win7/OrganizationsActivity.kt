package com.example.win7

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.win7.api.SportsDataApi
import com.example.win7.model.Organization
import com.example.win7.model.Termin
import com.example.win7.ui.theme.MyGray
import com.example.win7.ui.theme.Win7Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

var arrayListOrg = ArrayList<Organization>()
var orgListResponse: List<Organization> by mutableStateOf(listOf())

class OrganizationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetData()
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(MyGray)
            ) {
                itemsIndexed(orgListResponse) { index, item ->
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
                                Image(
                                    painter = rememberAsyncImagePainter(model = item.img),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .padding(10.dp)
                                        .size(300.dp),
                                    contentDescription = ""
                                )
                            }
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
        val response = api.getOrganiztions().awaitResponse()
        launch(Dispatchers.Main) {
            orgListResponse = response.body()!!.organizations
            arrayListOrg = orgListResponse as ArrayList<Organization>
        }
    }
}
