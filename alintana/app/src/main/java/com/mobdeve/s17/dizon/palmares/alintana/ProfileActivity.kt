package com.mobdeve.s17.dizon.palmares.alintana

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val user = intent.getSerializableExtra("user") as? User

        Log.v("USER", user!!.toString())

        title = "Profile"
        //supportActionBar?.elevation = 0F

        binding.tvProfileUsername.text = user.username
        binding.tvProfileHeadline.text = user.headline

        if(user.userImage != null && !isEmpty(user.userImage)){
            Picasso.get().load(user.userImage).into(binding.ivProfileImage)
        }
        else {
            binding.ivProfileImage.setImageResource(R.drawable.ic_baseline_person_24)
        }

        binding.tvProfileAge.text = user.getAge().toString()
        binding.tvProfileSex.text =  user.sex
        binding.tvProfileLoc.text =  user.location

        if(user.sex.equals("Prefer Not to Say", true)){
            binding.tvProfileSep1.visibility = View.GONE
            binding.tvProfileSex.visibility = View.GONE
        }
        if(isEmpty(user.location)){
            binding.tvProfileSep2.visibility = View.GONE
            binding.tvProfileLoc.visibility = View.GONE
        }

        binding.tvProfileLvl.text = (user.experience/100).toString()
        binding.progressBar.progress = user.experience%100
        binding.progressBar.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.primary))

        // toolbar
        setSupportActionBar(binding.toolbarProfile)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.dlProfile,
            binding.toolbarProfile,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )

        binding.dlProfile.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

        setContentView(binding.root)
    }

    // navigation bar functions
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        TODO("Not yet implemented")
    }
}