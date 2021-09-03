package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGamesListBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentMatchBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseProfileFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class GamesListFragment : BaseProfileFragment(){

    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        user= ACTIVITY.user
        ACTIVITY.title = "Games"


        _binding = FragmentGamesListBinding.inflate(inflater, container, false)

        binding.btnPlay.setOnClickListener {
            val gotoGame = Intent(activity?.baseContext, GameActivity::class.java)
            gotoGame.putExtra("user", user)
            startActivity(gotoGame)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}