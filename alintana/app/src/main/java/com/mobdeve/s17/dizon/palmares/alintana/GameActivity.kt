package com.mobdeve.s17.dizon.palmares.alintana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}