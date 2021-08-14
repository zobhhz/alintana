package com.mobdeve.s17.dizon.palmares.alintana

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(WelcomeFragment())

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


    }



    fun loadFragment(fragment: Fragment){

        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}