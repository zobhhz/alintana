package com.mobdeve.s17.dizon.palmares.alintana.model.response

import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.model.User

data class RegisterResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("data")
    val data: User
)

