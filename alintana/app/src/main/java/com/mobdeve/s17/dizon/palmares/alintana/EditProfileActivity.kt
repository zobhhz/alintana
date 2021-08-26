package com.mobdeve.s17.dizon.palmares.alintana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityEditProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import kotlinx.coroutines.*
import kotlin.system.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        val sexList = listOf("Prefer Not To Say", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val sexAdapter = ArrayAdapter(applicationContext, R.layout.item_list, sexList)
        (binding.actvSex)?.setAdapter(sexAdapter)

        val prefList = listOf("Any", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val prefAdapter = ArrayAdapter(applicationContext, R.layout.item_list, prefList)
        (binding.actvPref)?.setAdapter(prefAdapter)
        Log.d("GETTING USER", "USER")
        user = async {User("6123d3c9825c9e0a44c7a952")}
        Log.d("ON START", "HELLOOOOOOOOOOO")

        Log.d("GUSER:", "HELOOO")
        binding.etUsername.setText(user.username)
        binding.etBirthdate.setText(user.birthdate)
        binding.actvSex.setText(user.sex)
        binding.etHeadline.setText(user.headline)
        binding.actvMobilenumber.setText(user.mobileNumber)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()


    }
}