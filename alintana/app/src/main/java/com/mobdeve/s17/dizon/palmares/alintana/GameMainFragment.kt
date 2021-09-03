package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameMainBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseGameFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.User

/**
 * A simple [Fragment] subclass.
 * Use the [GameMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameMainFragment : BaseGameFragment() {
    private var _binding: FragmentGameMainBinding? = null
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

        _binding = FragmentGameMainBinding.inflate(inflater,container,false)

        ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnGameCategory1.setOnClickListener {
            (requireActivity() as GameActivity).loadFragment(GameQuestionFragment())
            Log.v("GameMainFragment","Button pressed")
        }

        binding.btnLeaderboard1.root.text = "Food"
        binding.btnLeaderboard2.root.text = "Sports"
        binding.btnLeaderboard3.root.text = "Pop Culture"
        binding.btnLeaderboard4.root.text = "Games"

        // Inflate the layout for this fragment
        return binding.root
    }
}