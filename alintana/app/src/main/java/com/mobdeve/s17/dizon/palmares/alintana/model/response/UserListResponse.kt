package com.mobdeve.s17.dizon.palmares.alintana.model.response

import com.google.gson.annotations.SerializedName
import com.mobdeve.s17.dizon.palmares.alintana.model.User

class UserListResponse {
    @SerializedName("count")
    var count: Int = -1

    @SerializedName("next")
    var next: String = ""

    @SerializedName("previous")
    var previous: String = ""

    @SerializedName("results")
    var userList: ArrayList<User> = ArrayList<User>()
}