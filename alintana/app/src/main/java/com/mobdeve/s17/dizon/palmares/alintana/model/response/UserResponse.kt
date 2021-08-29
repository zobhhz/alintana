package com.mobdeve.s17.dizon.palmares.alintana.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id")
    var _id : String,
    @SerializedName("username")
var username : String,
@SerializedName("birthdate")
var birthdate : String,
@SerializedName("sex")
var sex : String,
@SerializedName("mobileNumber")
var mobileNumber : String,
@SerializedName("location")
var location : String,
@SerializedName("headline")
var headline : String,
@SerializedName("experience")
var experience : Int,
@SerializedName("createdAt")
var createdAt : String,
@SerializedName("userimg")
var userImage : String,

)
