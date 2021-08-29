package com.mobdeve.s17.dizon.palmares.alintana.model.response

import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.model.User

data class LoginResponse(val status: String, val message: String, @SerializedName("data") val data: User)