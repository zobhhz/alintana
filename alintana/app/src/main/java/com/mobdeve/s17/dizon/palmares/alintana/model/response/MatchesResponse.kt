package com.mobdeve.s17.dizon.palmares.alintana.model.response

import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.model.User

data class MatchesResponse (

    @SerializedName("status")
    var status :String,

    @SerializedName("length")
    var count : String,

    @SerializedName("data")
    var matches : ArrayList<User>

    )
