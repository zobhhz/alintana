package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.api.APIInterface
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameMainBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseGameFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.Quiz
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [GameMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameMainFragment : BaseGameFragment() {
    private var _binding: FragmentGameMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User
    private lateinit var client : APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = APIClient.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user= ACTIVITY.user

        _binding = FragmentGameMainBinding.inflate(inflater,container,false)

        ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // quizzes
        binding.btnGameCategory1.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            getGameByCategory("Food")
            Log.v("GameMainFragment","Food quiz pressed")
        }

        binding.btnGameCategory2.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            getGameByCategory("Sports")
            Log.v("GameMainFragment","Sports quiz pressed")
        }

        binding.btnGameCategory3.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            getGameByCategory("Pop Culture")
            Log.v("GameMainFragment","Pop Culture quiz pressed")
        }

        binding.btnGameCategory4.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            getGameByCategory("Games")
            Log.v("GameMainFragment","Games quiz pressed")
        }

        // leaderboards
        binding.btnLeaderboard1.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            Log.v("GameMainFragment","Food Leaderboard pressed")
        }

        binding.btnLeaderboard2.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            Log.v("GameMainFragment","Sports Leaderboard pressed")
        }

        binding.btnLeaderboard3.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            Log.v("GameMainFragment","Pop Culture Leaderboard pressed")
        }

        binding.btnLeaderboard4.setOnClickListener {
            ACTIVITY.sfx.clickSoundEffect()
            Log.v("GameMainFragment","Games Leaderboard pressed")
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getGameByCategory(categ: String){
        client.getQuiz(categ).enqueue(object: Callback<Quiz>{
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                ACTIVITY.quiz = response.body()!!
                Log.d("Quiz:", ACTIVITY.quiz.toString())
                (requireActivity() as GameActivity).loadFragment(GameQuestionFragment())
            }

            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getLeaderboardByCategory(categ: String){
        //TODO
    }
}