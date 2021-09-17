package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Leaderboard : Serializable{
    @SerializedName("status")
    var status = ""
    @SerializedName("quizMatches")
    var quizMatches : ArrayList<LeaderboardEntry>? = null

    constructor(quizMatches: ArrayList<LeaderboardEntry>?) {
        this.quizMatches = quizMatches
    }
}