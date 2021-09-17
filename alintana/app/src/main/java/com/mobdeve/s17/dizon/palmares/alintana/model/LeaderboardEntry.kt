package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LeaderboardEntry: Serializable {
    @SerializedName("user")
    var user = ""

    @SerializedName("percent")
    var percent = 0

    constructor(user: String, percent: Int) {
        this.user = user
        this.percent = percent
    }
}