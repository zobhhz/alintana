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
        userImage: String?
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
    }

    constructor(id : String){
        var call = APIClient.create().getUserById(id);

        try{
            var result: Response<UserResponse> = call.execute()

            this._id = result.body()!!._id
            this.username = result.body()!!.username
            this.sex = result.body()!!.sex
            this.mobileNumber = result.body()!!.mobileNumber
            this.birthdate = convertJSONDateToString(result.body()!!.birthdate)
            this.location = result.body()!!.location
            this.experience = result.body()!!.experience
            this.createdAt = convertJSONDateToString(result.body()!!.createdAt)

            if (result.body()!!.location != null) {
                this.location = result.body()!!.location
            }
            if (result.body()!!.headline != null) {
                this.headline = result.body()!!.headline
            }
            if (result.body()!!.userImage != null){
                this.userImage = result.body()!!.userImage
            }
        }catch(e : IOException){
            Log.e("CANNOT FIND USER", e.message.toString())
        }

        /*
        enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.v("USER: ", response.toString())
                _id = response.body()!!._id
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
                if(response.body()?.userImage != null){
                    userImage = response.body()!!.userImage
                }

            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

         */



    }

    fun getAge():Int{
        val today : Calendar = Calendar.getInstance()
        val dob : Calendar = Calendar.getInstance()
        Log.v("AGE", this.birthdate)

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



}