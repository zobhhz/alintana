package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameQuestionBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseGameFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.Quiz
import com.mobdeve.s17.dizon.palmares.alintana.model.User

/**
 * A simple [Fragment] subclass.
 * Use the [GameQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameQuestionFragment : BaseGameFragment() {
    private var _binding: FragmentGameQuestionBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User
    private var positions = arrayOf(false,false,false,false);
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user= ACTIVITY.user

        _binding = FragmentGameQuestionBinding.inflate(inflater,container,false)

        ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.btnNext.setOnClickListener {
            //(requireActivity() as GameActivity).loadFragment(GameQuestionFragment())
            Log.v("GameQuestionFragment","Button pressed")
        }
         var currQuiz : Quiz = ACTIVITY.quiz

        // initialize first question
        binding.indicator1.setColorFilter(resources.getColor(R.color.secondary))
        binding.tvQuestion.text = currQuiz.questions!![counter].question
        binding.btnGameAnswer1.text = currQuiz.questions!![counter].choices!![0]
        binding.btnGameAnswer2.text = currQuiz.questions!![counter].choices!![1]
        binding.btnGameAnswer3.text = currQuiz.questions!![counter].choices!![2]
        binding.btnGameAnswer4.text = currQuiz.questions!![counter].choices!![3]

        // onClick listener for answers
        binding.btnGameAnswer1.setOnClickListener {
            changeButtonColor(0)
        }

        binding.btnGameAnswer2.setOnClickListener {
            changeButtonColor(1)
        }

        binding.btnGameAnswer3.setOnClickListener {
            changeButtonColor(2)
        }

        binding.btnGameAnswer4.setOnClickListener {
            changeButtonColor(3)
        }
        /*
        ang gusto natin mangyari is that load muna yung first question
        once nagclick to next, show question #2 and repeat until question #5 is shown
        once nasa question #5 na, pressing next will show the leaderboard na
         */
        binding.btnNext.setOnClickListener {
            // increment counter
            counter++
            Log.d("Question index", counter.toString())
            if(counter < currQuiz.questions!!.size){
                //change question
                binding.tvQuestion.text = currQuiz.questions!![counter].question
                binding.btnGameAnswer1.text = currQuiz.questions!![counter].choices!![0]
                binding.btnGameAnswer2.text = currQuiz.questions!![counter].choices!![1]
                binding.btnGameAnswer3.text = currQuiz.questions!![counter].choices!![2]
                binding.btnGameAnswer4.text = currQuiz.questions!![counter].choices!![3]

                //change indicator and reset button colors
                changeIndicatorColor()
                changeButtonColor(4)
            }
            else {
                Log.d("going to leaderboard",counter.toString())
                (requireActivity() as GameActivity).loadFragment(GameLeaderboardFragment())
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun changeIndicatorColor() {
        when(counter) {
            1 -> {
                binding.indicator1.colorFilter = null
                binding.indicator2.setColorFilter(resources.getColor(R.color.secondary))
            }
            2 -> {
                binding.indicator2.colorFilter = null
                binding.indicator3.setColorFilter(resources.getColor(R.color.secondary))
            }
            3 -> {
                binding.indicator3.colorFilter = null
                binding.indicator4.setColorFilter(resources.getColor(R.color.secondary))
            }
            4 -> {
                binding.indicator4.colorFilter = null
                binding.indicator5.setColorFilter(resources.getColor(R.color.secondary))
            }
        }
    }

    private fun changeButtonColor(index :Int ) {
        when(index) {
            0 -> {
                setButton1Color("on")
                setButton2Color("off")
                setButton3Color("off")
                setButton4Color("off")
            }
            1 -> {
                setButton1Color("off")
                setButton2Color("on")
                setButton3Color("off")
                setButton4Color("off")
            }
            2 -> {
                setButton1Color("off")
                setButton2Color("off")
                setButton3Color("on")
                setButton4Color("off")
            }
            3 -> {
                setButton1Color("off")
                setButton2Color("off")
                setButton3Color("off")
                setButton4Color("on")
            }
            4 -> { // off all
                setButton1Color("off")
                setButton2Color("off")
                setButton3Color("off")
                setButton4Color("off")
            }
        }
    }

    private fun setButton1Color(status: String) {
        if(status == "on") {
            binding.btnGameAnswer1.setBackgroundColor(resources.getColor(R.color.primary))
            binding.btnGameAnswer1.setTextColor(resources.getColor(R.color.white))
            positions[0] = true
        }
        else {
            binding.btnGameAnswer1.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnGameAnswer1.setTextColor(resources.getColor(R.color.primary))
            positions[0] = false
        }
    }

    private fun setButton2Color(status: String) {
        if(status == "on") {
            binding.btnGameAnswer2.setBackgroundColor(resources.getColor(R.color.primary))
            binding.btnGameAnswer2.setTextColor(resources.getColor(R.color.white))
            positions[1] = true
        }
        else {
            binding.btnGameAnswer2.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnGameAnswer2.setTextColor(resources.getColor(R.color.primary))
            positions[1] = false
        }
    }

    private fun setButton3Color(status: String) {
        if(status == "on") {
            binding.btnGameAnswer3.setBackgroundColor(resources.getColor(R.color.primary))
            binding.btnGameAnswer3.setTextColor(resources.getColor(R.color.white))
            positions[2] = true
        }
        else {
            binding.btnGameAnswer3.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnGameAnswer3.setTextColor(resources.getColor(R.color.primary))
            positions[2] = false
        }
    }

    private fun setButton4Color(status: String) {
        if(status == "on") {
            binding.btnGameAnswer4.setBackgroundColor(resources.getColor(R.color.primary))
            binding.btnGameAnswer4.setTextColor(resources.getColor(R.color.white))
            positions[3] = true
        }
        else {
            binding.btnGameAnswer4.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnGameAnswer4.setTextColor(resources.getColor(R.color.primary))
            positions[3] = false
        }
    }
}