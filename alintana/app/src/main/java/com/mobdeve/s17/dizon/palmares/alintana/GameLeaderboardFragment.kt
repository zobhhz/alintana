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

        //var currLeaderboard = ACTIVITY.leaderboard
        var currQuizCateg = ACTIVITY.quiz.category

        binding.tvTitle.text = "Your Top 5 Matches for " + currQuizCateg

//        // if leaderboard is not empty
//        if(currLeaderboard.quizMatches!!.size > 0) {
//            binding.tvSubtitle.text = R.string.default_subtitle.toString()
//            binding.tvLeaderboardUser1 = currLeaderboard[0].user
//            binding.tvLeaderboardPercent1 = currLeaderboard[0].percent
//        }
//        //leaderboard is empty, don't show card and have prompt
//        else {
//            binding.tvSubtitle.text = "No leaderboard found!"
//            binding.cvLeaderboard.visibility = View.GONE
//        }

        binding.btnContinue.setOnClickListener {
            (requireActivity() as GameActivity).loadFragment(GameMainFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}