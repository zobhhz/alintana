package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("data")
    val data: User
)

