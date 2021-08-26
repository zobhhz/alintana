package com.mobdeve.s17.dizon.palmares.alintana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
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
        user = (intent.getSerializableExtra("user") as? User)!!

        binding.etUsername.setText(user.username)
        binding.etBirthdate.setText(user.birthdate)
        binding.actvSex.setText(sexAdapter.getItem(sexAdapter.getPosition(user.sex)), false)
        binding.actvPref.setText(prefAdapter.getItem(0), false)
        binding.etHeadline.setText(user.headline)
        binding.actvLoc.setText(user.location)
        binding.actvMobilenumber.setText(user.mobileNumber)
        setContentView(binding.root)
    }

}