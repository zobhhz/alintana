package com.mobdeve.s17.dizon.palmares.alintana.helpers

import android.content.Context
import androidx.fragment.app.Fragment
import com.mobdeve.s17.dizon.palmares.alintana.ProfileActivity


abstract class BaseProfileFragment : Fragment() {

    lateinit var ACTIVITY: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as ProfileActivity
    }
}