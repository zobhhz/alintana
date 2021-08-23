package com.mobdeve.s17.dizon.palmares.alintana

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityMainBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val user = intent.getSerializableExtra("user") as? User

        Log.v("USER", user!!.toString())

        binding.tvText.text = "Hello " + user.username
        binding.progressBar.progress = 50
        binding.progressBar.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.primary))
        setContentView(binding.root)

    }

}