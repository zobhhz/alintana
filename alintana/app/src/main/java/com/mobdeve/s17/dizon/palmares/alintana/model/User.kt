package com.mobdeve.s17.dizon.palmares.alintana.model

import java.io.Serializable
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    var username = ""
    var birthdate = ""
    var sex = ""
    var mobileNumber = ""
    var location = ""
    var headline = ""
    var experience = 0
    var createdAt = ""

    constructor(
        username: String,
        birthdate: String?,
        sex: String,
        mobileNumber: String?,
        location: String?,
        headline: String?,
        experience: Int,
        createdAt: String,
    ) {

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