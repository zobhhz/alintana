package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName

data class PossibleMatchesResponse (

    @SerializedName("status")
    var status :String,

    @SerializedName("length")
    var count : String,

    @SerializedName("data")
    var matches : ArrayList<User>

    )
