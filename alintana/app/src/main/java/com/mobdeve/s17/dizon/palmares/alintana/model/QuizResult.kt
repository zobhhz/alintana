package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuizResult : Serializable {

    @SerializedName("user")
    var user : User? = null
    @SerializedName("category")
    var category = ""
    @SerializedName("answer1")
    var answer1 = ""
    @SerializedName("answer2")
    var answer2 = ""
    @SerializedName("answer3")
    var answer3 = ""
    @SerializedName("answer4")
    var answer4 = ""
    @SerializedName("answer5")
    var answer5 = ""
}