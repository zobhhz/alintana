package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityGameBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.SoundEffects
import com.mobdeve.s17.dizon.palmares.alintana.model.Leaderboard
import com.mobdeve.s17.dizon.palmares.alintana.model.Quiz
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.mobdeve.s17.dizon.palmares.alintana.services.MediaPlayerService

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    lateinit var user : User
    lateinit var quiz : Quiz
    lateinit var leaderboard: Leaderboard
    lateinit var sfx : SoundEffects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        user = intent.getSerializableExtra("user") as User
        Log.v("USER", user.toString())

        sfx = SoundEffects(applicationContext)
        startService( Intent(applicationContext, MediaPlayerService::class.java ))

        loadFragment(GameMainFragment())

        // setting toolbar as action bar
        setSupportActionBar(binding.toolbarGame)

        //changing status bar color
        window.statusBarColor = getColor(R.color.secondary)

        // hiding default action bar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setContentView(binding.root)
    }


    fun loadFragment(fragment: Fragment){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService( Intent(applicationContext, MediaPlayerService::class.java ))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                Log.d("HELLO", "WORLD")
                val gotoProfile: Intent = Intent(baseContext, ProfileActivity::class.java)
                gotoProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                gotoProfile.putExtra("user", user)
                startActivity(gotoProfile)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}