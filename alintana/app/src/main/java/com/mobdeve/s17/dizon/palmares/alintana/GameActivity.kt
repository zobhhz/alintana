package com.mobdeve.s17.dizon.palmares.alintana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityGameBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        user = intent.getSerializableExtra("user") as User
        Log.v("USER", user.toString())

        loadFragment(GameMainFragment())

        // setting toolbar as action bar
        setSupportActionBar(binding.toolbarGame)

        //changing status bar color
        window.statusBarColor = getColor(R.color.secondary)

        // hiding default action bar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setContentView(binding.root)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


    fun loadFragment(fragment: Fragment){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}