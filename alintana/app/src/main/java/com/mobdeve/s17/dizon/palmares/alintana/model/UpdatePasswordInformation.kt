package com.mobdeve.s17.dizon.palmares.alintana.model

class UpdatePasswordInformation {

    var id = ""

    var oldPassword = ""
    var newPassword = ""
    var confirmNewPassword = ""


    constructor(id: String,
                oldPassword: String,
                newPassword: String,
                confirmNewPassword: String) {
        this.id = id
        this.oldPassword = oldPassword
        this.newPassword = newPassword
        this.confirmNewPassword = confirmNewPassword
    }



}