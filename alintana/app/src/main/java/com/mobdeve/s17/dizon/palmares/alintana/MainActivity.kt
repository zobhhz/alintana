package com.mobdeve.s17.dizon.palmares.alintana

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityMainBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentWelcomeBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.SoundEffects
import com.mobdeve.s17.dizon.palmares.alintana.services.MediaPlayerService

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var welcomeBinding: FragmentWelcomeBinding
    lateinit var sfx : SoundEffects


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        welcomeBinding = FragmentWelcomeBinding.inflate(layoutInflater)

        loadFragment(WelcomeFragment())
        setContentView(binding.root)

        sfx = SoundEffects(applicationContext)


        binding.tvWelcome.setOnClickListener{
            sfx.clickSoundEffect()
            binding.tvWelcome.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvSignUp.setBackgroundResource(0)
            loadFragment(WelcomeFragment())
        }
        binding.tvLogin.setOnClickListener{
            sfx.clickSoundEffect()
            binding.tvLogin.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvSignUp.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(LoginFragment())
        }

        binding.tvSignUp.setOnClickListener{
            sfx.clickSoundEffect()
            binding.tvSignUp.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(SignUpFragment())
        }

//        var welcomeSignupBtn = this.findViewById(R.id.btn_welcome_signup) as Button
//        var welcomeLoginBtn = this.findViewById(R.id.btn_welcome_login) as Button

        welcomeBinding.btnWelcomeSignup.setOnClickListener{
            sfx.clickSoundEffect()
            binding.tvSignUp.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(SignUpFragment())
        }

        welcomeBinding.btnWelcomeLogin.setOnClickListener{
            sfx.clickSoundEffect()
            binding.tvLogin.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvSignUp.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(LoginFragment())
        }
    }

    fun loadFragment(fragment: Fragment){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}