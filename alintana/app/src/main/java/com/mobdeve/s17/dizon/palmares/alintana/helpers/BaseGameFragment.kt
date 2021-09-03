package com.mobdeve.s17.dizon.palmares.alintana.helpers

import android.content.Context
import androidx.fragment.app.Fragment
import com.mobdeve.s17.dizon.palmares.alintana.GameActivity

abstract class BaseGameFragment : Fragment() {

    lateinit var ACTIVITY: GameActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as GameActivity
    }
}