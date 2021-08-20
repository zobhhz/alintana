package com.mobdeve.s17.dizon.palmares.alintana.model

import com.google.gson.annotations.SerializedName

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