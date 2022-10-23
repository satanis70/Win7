package com.example.win7.api

import com.example.win7.model.PopularFightersModel
import com.example.win7.model.PopularOrganizations
import com.example.win7.model.TerminsModel
import retrofit2.Call
import retrofit2.http.GET

interface SportsDataApi {
    @GET("/win7/most_popular_fighters.json")
    fun getFighters(): Call<PopularFightersModel>
    @GET("/win7/termins.json")
    fun getTermins(): Call<TerminsModel>
    @GET("/win7/popular_org.json")
    fun getOrganiztions(): Call<PopularOrganizations>
}