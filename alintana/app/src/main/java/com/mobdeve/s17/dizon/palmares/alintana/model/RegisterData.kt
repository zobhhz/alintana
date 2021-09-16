package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RegisterData : Serializable {
    @SerializedName("newUser")
    var user : User? = null

    constructor(user: User?) {
        this.user = user
    }
}