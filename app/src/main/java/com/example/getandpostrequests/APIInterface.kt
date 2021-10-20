package com.example.getandpostrequests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @GET("/custom-people/")
    fun doGetListResources(): Call<List<PeopleDetails.Datum>>
    @POST("/custom-people/")
    fun addUser(@Body userData: PeopleDetails.Datum): Call<PeopleDetails.Datum>
}