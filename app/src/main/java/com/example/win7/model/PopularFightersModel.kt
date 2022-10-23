package com.example.win7.model


import com.google.gson.annotations.SerializedName

data class PopularFightersModel(
    @SerializedName("popular_fighters")
    val popularFighters: ArrayList<PopularFighter>
)