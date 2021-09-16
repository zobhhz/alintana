package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Question : Serializable {
    @SerializedName("question")
    var question = ""

    @SerializedName("choices")
    var choices : ArrayList<String>? = null

    constructor(question: String, choices: ArrayList<String>?) {
        this.question = question
        this.choices = choices
    }
}