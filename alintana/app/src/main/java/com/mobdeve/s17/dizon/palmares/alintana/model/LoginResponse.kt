package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(val status: String, val message: String, @SerializedName("data") val data: User)