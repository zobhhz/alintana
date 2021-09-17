package com.mobdeve.s17.dizon.palmares.alintana.model

class AddQuizResultInformation {
    var user = ""
    var category = ""
    var answer1 = ""
    var answer2 = ""
    var answer3 = ""
    var answer4 = ""
    var answer5 = ""

    constructor(
        user: String,
        category : String,
        answer1 : String,
        answer2 : String,
        answer3 : String,
        answer4 : String,
        answer5 : String
    ) {
        this.user = user
        this.category = category
        this.answer1 = answer1
        this.answer2 = answer2
        this.answer3 = answer3
        this.answer4 = answer4
        this.answer5 = answer5
    }
}