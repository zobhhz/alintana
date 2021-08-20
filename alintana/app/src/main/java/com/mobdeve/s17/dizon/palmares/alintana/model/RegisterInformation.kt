package com.mobdeve.s17.dizon.palmares.alintana.model

class RegisterInformation {
    var username = ""
    var birthdate = ""
    var sex = ""
    var mobileNumber = ""
    var location = ""
    var headline = ""
    var password = ""
    var confirmPassword = ""

    constructor(
        username: String,
        birthdate: String,
        sex: String,
        mobileNumber: String,
        location: String,
        headline: String,
        password: String,
        confirmPassword: String
    ) {
        this.username = username
        this.birthdate = birthdate
        this.sex = sex
        this.mobileNumber = mobileNumber
        this.location = location
        this.headline = headline
        this.password = password
        this.confirmPassword = confirmPassword
    }
}