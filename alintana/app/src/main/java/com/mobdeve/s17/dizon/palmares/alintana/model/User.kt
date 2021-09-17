package com.mobdeve.s17.dizon.palmares.alintana.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.model.response.UserResponse
import retrofit2.Response
import java.io.IOException
import java.io.Serializable
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    @SerializedName("_id")
    var _id = ""
    @SerializedName("username")
    var username = ""
    @SerializedName("birthdate")
    var birthdate = ""
    @SerializedName("sex")
    var sex = ""
    @SerializedName("mobileNumber")
    var mobileNumber = ""
    @SerializedName("location")
    var location = ""
    @SerializedName("headline")
    var headline = ""
    @SerializedName("experience")
    var experience = 0
    @SerializedName("createdAt")
    var createdAt = ""
    @SerializedName("userimg")
    var userImage = ""
    @SerializedName("preference")
    var preference = ""
    @SerializedName("dailySwipeLeft")
    var dailySwipeLeft = 0
    @SerializedName("dailySwipeRight")
    var dailySwipeRight = 0
    @SerializedName("dailyGame")
    var dailyGame = 0
    @SerializedName("dailyMatch")
    var dailyMatch = 0
    @SerializedName("allTimeSwipeLeft")
    var allTimeSwipeLeft = 0
    @SerializedName("allTimeSwipeRight")
    var allTimeSwipeRight = 0
    @SerializedName("allTimeGame")
    var allTimeGame = 0
    @SerializedName("allTimeMatch")
    var allTimeMatch = 0




    constructor(
        _id : String,
        username: String,
        birthdate: String?,
        sex: String,
        mobileNumber: String?,
        location: String?,
        headline: String?,
        experience: Int,
        createdAt: String,
        preference: String,
    ) {
        this._id = _id
        this.username = username
        this.birthdate = convertJSONDateToString(birthdate!!)
        this.sex = sex
        if (mobileNumber != null) {
            this.mobileNumber = mobileNumber
        }
        if (location != null) {
            this.location = location
        }
        if (headline != null) {
            this.headline = headline
        }
        this.experience = experience
        this.createdAt = createdAt
        this.preference = preference
    }

    constructor(
        _id : String,
        username: String,
        birthdate: String?,
        sex: String,
        mobileNumber: String?,
        location: String?,
        headline: String?,
        experience: Int,
        createdAt: String,
        preference: String,
        dailySwipeLeft: Int,
        dailySwipeRight: Int,
        dailyGame: Int,
        dailyMatch: Int,
        allTimeSwipeLeft: Int,
        allTimeSwipeRight: Int,
        allTimeGame: Int,
        allTimeMatch: Int

    ) {
        this._id = _id
        this.username = username
        this.birthdate = convertJSONDateToString(birthdate!!)
        this.sex = sex
        if (mobileNumber != null) {
            this.mobileNumber = mobileNumber
        }
        if (location != null) {
            this.location = location
        }
        if (headline != null) {
            this.headline = headline
        }
        this.experience = experience
        this.createdAt = createdAt
        this.preference = preference
        this.dailyGame = dailyGame
        this.dailyMatch = dailyMatch
        this.dailySwipeLeft = dailySwipeLeft
        this.dailySwipeRight = dailySwipeRight
        this.allTimeGame = allTimeGame
        this.allTimeMatch = allTimeMatch
        this.allTimeSwipeLeft = allTimeSwipeLeft
        this.allTimeSwipeRight = allTimeSwipeRight

    }

    constructor(
        _id : String,
        username: String,
        birthdate: String?,
        sex: String,
        mobileNumber: String?,
        location: String?,
        headline: String?,
        experience: Int,
        createdAt: String,
        userImage: String?,
        preference: String,
        dailySwipeLeft: Int,
        dailySwipeRight: Int,
        dailyGame: Int,
        dailyMatch: Int,
        allTimeSwipeLeft: Int,
        allTimeSwipeRight: Int,
        allTimeGame: Int,
        allTimeMatch: Int
        ) {
        this._id = _id
        this.username = username
        this.birthdate = convertJSONDateToString(birthdate!!)
        this.sex = sex
        if (mobileNumber != null) {
            this.mobileNumber = mobileNumber
        }
        if (location != null) {
            this.location = location
        }
        if (headline != null) {
            this.headline = headline
        }
        this.experience = experience
        this.createdAt = convertJSONDateToString(createdAt)
        if (userImage != null){
            this.userImage = userImage
        }
        this.preference = preference
        this.dailyGame = dailyGame
        this.dailyMatch = dailyMatch
        this.dailySwipeLeft = dailySwipeLeft
        this.dailySwipeRight = dailySwipeRight
        this.allTimeGame = allTimeGame
        this.allTimeMatch = allTimeMatch
        this.allTimeSwipeLeft = allTimeSwipeLeft
        this.allTimeSwipeRight = allTimeSwipeRight

    }

    fun getAge():Int{
        val today : Calendar = Calendar.getInstance()
        val dob : Calendar = Calendar.getInstance()

        if(!this.birthdate.contains("/"))
            this.birthdate = convertJSONDateToString(this.birthdate)


        var dates = this.birthdate.split("/")
        val year = parseInt(dates[2])
        val month = parseInt(dates[0])
        val day = parseInt(dates[1])

        dob.set(year,month,day)

        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
            age--

        return age
    }

    fun getTextBoD():String{
        val parse = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.birthdate.substring(0,10))
        return SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(parse)
    }
    fun convertJSONDateToString(jsonString : String): String{
        val parse = SimpleDateFormat("yyyy-MM-dd").parse(jsonString.substring(0,10))
        return SimpleDateFormat("MM/dd/yyyy").format(parse)
    }

    fun isMyImageGIF(): Boolean {
        if(this.userImage !== ""){
            val segment = userImage.split(".")
            return segment[segment.size - 1] == "gif"
        }
        return false
    }



}