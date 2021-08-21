package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    @SerializedName("__id")
    var __id = ""
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

    constructor(
        __id : String,
        username: String,
        birthdate: String?,
        sex: String,
        mobileNumber: String?,
        location: String?,
        headline: String?,
        experience: Int,
        createdAt: String,
    ) {
        this.__id = __id
        this.username = username
        this.birthdate = birthdate!!
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
    }

    constructor(id : String){
        APIClient.create().getUserById(id).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                __id = response.body()!!.__id
                username = response.body()!!.username
                birthdate = response.body()!!.birthdate
                sex = response.body()!!.sex
                experience = response.body()!!.experience
                createdAt = response.body()!!.createdAt
                if(response.body()?.location != null){
                    location = response.body()!!.location
                }
                if(response.body()?.headline != null){
                    headline = response.body()!!.headline
                }
                if(response.body()?.mobileNumber != null){
                    mobileNumber = response.body()!!.mobileNumber
                }



            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })



    }

    fun getAge():Int{
        val today : Calendar = Calendar.getInstance()
        val dob : Calendar = Calendar.getInstance()

        val year = parseInt(this.birthdate.substring(0,4))
        val month = parseInt(this.birthdate.substring(5,7))
        val day = parseInt(this.birthdate.substring(8,10))

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
}