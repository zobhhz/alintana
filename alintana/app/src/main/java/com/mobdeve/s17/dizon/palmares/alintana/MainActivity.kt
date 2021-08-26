package com.mobdeve.s17.dizon.palmares.alintana

import android.graphics.drawable.Drawable
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var welcomeBinding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        welcomeBinding = FragmentWelcomeBinding.inflate(layoutInflater)

        loadFragment(WelcomeFragment())


        setContentView(binding.root)

        binding.tvWelcome.setOnClickListener{
            binding.tvWelcome.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvSignUp.setBackgroundResource(0)
            loadFragment(WelcomeFragment())
        }
        binding.tvLogin.setOnClickListener{
            binding.tvLogin.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvSignUp.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(LoginFragment())
        }

        binding.tvSignUp.setOnClickListener{
            binding.tvSignUp.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(SignUpFragment())
        }

//        var welcomeSignupBtn = this.findViewById(R.id.btn_welcome_signup) as Button
//        var welcomeLoginBtn = this.findViewById(R.id.btn_welcome_login) as Button

        welcomeBinding.btnWelcomeSignup.setOnClickListener{
            Log.v("TEST", "TEST")
            binding.tvSignUp.background = resources.getDrawable(R.drawable.textlines, theme)
            binding.tvLogin.setBackgroundResource(0)
            binding.tvWelcome.setBackgroundResource(0)
            loadFragment(SignUpFragment())
        }

        welcomeBinding.btnWelcomeLogin.setOnClickListener{
            Log.v("TEST", "TEST")
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