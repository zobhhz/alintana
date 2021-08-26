package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Context
import androidx.fragment.app.Fragment


abstract class BaseProfileFragment : Fragment() {

    lateinit var ACTIVITY: ProfileActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as ProfileActivity
    }
}