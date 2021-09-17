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

        var currLeaderboard = ACTIVITY.leaderboard
        var currQuizCateg = ACTIVITY.quiz.category


        // if leaderboard is not empty
        if(currLeaderboard.quizMatches!!.size > 0) {
            binding.tvTitle.text = "Your Top ${ACTIVITY.leaderboard.quizMatches!!.size} Matches for " + currQuizCateg

            binding.tvSubtitle.text = "These are your matches that have the most similar answers as yours! One of them might be a keeper ;)"

            binding.tvLeaderboardUser1.text = getValOrEmptyString(0, 1)
            binding.tvLeaderboardPercent1.text = getValOrEmptyString(0, 2)

            binding.tvLeaderboardUser2.text = getValOrEmptyString(1, 1)
            binding.tvLeaderboardPercent2.text = getValOrEmptyString(1, 2)
//            if(binding.tvLeaderboardUser2.text.toString().isEmpty()){
//                binding.tvLeaderboardUser2.visibility = View.GONE
//                binding.tvLeaderboardPercent2.visibility = View.GONE
//            }


            binding.tvLeaderboardUser3.text = getValOrEmptyString(2, 1)
            binding.tvLeaderboardPercent3.text = getValOrEmptyString(2, 2)
//            if(binding.tvLeaderboardUser3.text.toString().isEmpty()){
//                binding.tvLeaderboardUser3.visibility = View.GONE
//                binding.tvLeaderboardPercent3.visibility = View.GONE
//            }


            binding.tvLeaderboardUser4.text = getValOrEmptyString(3, 1)
            binding.tvLeaderboardPercent4.text = getValOrEmptyString(3, 2)


            binding.tvLeaderboardUser5.text = getValOrEmptyString(4, 1)
            binding.tvLeaderboardPercent5.text = getValOrEmptyString(4, 2)


        }
        //leaderboard is empty, don't show card and have prompt
        else {
            binding.tvTitle.text = "No Matches for " + currQuizCateg

            binding.tvSubtitle.text = "No leaderboard found!"
            binding.cvLeaderboard.visibility = View.GONE
        }

        binding.btnContinue.setOnClickListener {
            (requireActivity() as GameActivity).loadFragment(GameMainFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getValOrEmptyString(index: Int, type: Int): String{
       val value = ACTIVITY.leaderboard.quizMatches?.getOrNull(index)
        if(value == null)
            return ""
        else{
            if(type == 1)
                return ACTIVITY.leaderboard.quizMatches!![index].user
            else
                return ACTIVITY.leaderboard.quizMatches!![index].percent.toString() + "%"
        }
    }
}