package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User

class ProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityProfileBinding
    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)

        user = intent.getSerializableExtra("user") as User

        loadFragment(UserProfileFragment())

        Log.v("USER", user!!.toString())

        title = "Profile"

        // app bar text color
        binding.toolbarProfile.setTitleTextColor(resources.getColor(R.color.primary))

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

        // change hamburger icon color
        actionBarDrawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.primary)

        setContentView(binding.root)
    }

    // navigation bar functions
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id : Int = item.itemId
        when (id){
            R.id.menu_myprofile -> loadFragment(UserProfileFragment())
            R.id.menu_editprofile -> loadFragment(EditProfileFragment())
            R.id.menu_find_match -> loadFragment(MatchFragment())
            else -> loadFragment(UserProfileFragment())
        }
        return true
    }
    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        TODO("Not yet implemented")
    }

    fun loadFragment(fragment: Fragment){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}