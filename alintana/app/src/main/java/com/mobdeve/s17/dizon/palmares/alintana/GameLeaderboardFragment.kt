package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameLeaderboardBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameQuestionBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseGameFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.User

/**
 * A simple [Fragment] subclass.
 * Use the [GameLeaderboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameLeaderboardFragment : BaseGameFragment() {
    private var _binding: FragmentGameLeaderboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user= ACTIVITY.user

        _binding = FragmentGameLeaderboardBinding.inflate(inflater,container,false)

        ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.btnContinue.setOnClickListener {
            (requireActivity() as GameActivity).loadFragment(GameMainFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}