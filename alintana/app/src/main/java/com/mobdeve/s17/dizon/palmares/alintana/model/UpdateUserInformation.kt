package com.mobdeve.s17.dizon.palmares.alintana.model

class UpdateUserInformation {
    var id = ""

    var username = ""
    var birthdate = ""
    var sex = ""
    var mobileNumber = ""
    var location = ""
    var headline = ""
    var preference = ""
    var fileStr = ""

    constructor(
        id: String,
        username: String,
        birthdate: String,
        sex: String,
        mobileNumber: String,
        location: String,
        headline: String,
        preference: String,
        fileStr: String
    ) {
        this.id = id
        this.username = username
        this.birthdate = birthdate
        this.sex = sex
        this.mobileNumber = mobileNumber
        this.location = location
        this.headline = headline
        this.preference = preference
        this.fileStr = fileStr
    }
}