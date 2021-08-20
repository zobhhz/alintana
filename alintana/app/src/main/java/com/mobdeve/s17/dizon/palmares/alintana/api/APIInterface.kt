package com.mobdeve.s17.dizon.palmares.alintana.api

import com.mobdeve.s17.dizon.palmares.alintana.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @POST("/register")
    fun createUser(@Body registerInformation: RegisterInformation): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginInformation: LoginInformation) : Call<LoginResponse>



}