package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentGameQuestionBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseGameFragment
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

        val positions = arrayOf(false,false,false,false)
        binding.btnGameAnswer1.setOnClickListener {
            if(positions[0]) {
                binding.btnGameAnswer1.setBackgroundColor(resources.getColor(R.color.white))
                binding.btnGameAnswer1.setTextColor(resources.getColor(R.color.primary))
                positions[0] = false
            }
            else {
                binding.btnGameAnswer1.setBackgroundColor(resources.getColor(R.color.primary))
                binding.btnGameAnswer1.setTextColor(resources.getColor(R.color.white))
                positions[0] = true
            }
        }

        binding.btnGameAnswer2.setOnClickListener {
            if(positions[1]) {
                binding.btnGameAnswer2.setBackgroundColor(resources.getColor(R.color.white))
                binding.btnGameAnswer2.setTextColor(resources.getColor(R.color.primary))
                positions[1] = false
            }
            else {
                binding.btnGameAnswer2.setBackgroundColor(resources.getColor(R.color.primary))
                binding.btnGameAnswer2.setTextColor(resources.getColor(R.color.white))
                positions[1] = true
            }
        }

        binding.btnGameAnswer3.setOnClickListener {
            if(positions[2]) {
                binding.btnGameAnswer3.setBackgroundColor(resources.getColor(R.color.white))
                binding.btnGameAnswer3.setTextColor(resources.getColor(R.color.primary))
                positions[2] = false
            }
            else {
                binding.btnGameAnswer3.setBackgroundColor(resources.getColor(R.color.primary))
                binding.btnGameAnswer3.setTextColor(resources.getColor(R.color.white))
                positions[2] = true
            }
        }

        binding.btnGameAnswer4.setOnClickListener {
            if(positions[3]) {
                binding.btnGameAnswer4.setBackgroundColor(resources.getColor(R.color.white))
                binding.btnGameAnswer4.setTextColor(resources.getColor(R.color.primary))
                positions[3] = false
            }
            else {
                binding.btnGameAnswer4.setBackgroundColor(resources.getColor(R.color.primary))
                binding.btnGameAnswer4.setTextColor(resources.getColor(R.color.white))
                positions[3] = true
            }
        }

        binding.btnNext.setOnClickListener {
            (requireActivity() as GameActivity).loadFragment(GameLeaderboardFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}