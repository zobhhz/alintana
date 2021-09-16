package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Quiz : Serializable {
    @SerializedName("_id")
    var _id = ""

    @SerializedName("category")
    var category = ""

    @SerializedName("questions")
    var questions : ArrayList<Question>? = null

    constructor(_id: String, category: String, questions: ArrayList<Question>?) {
        this._id = _id
        this.category = category
        this.questions = questions
    }
}