package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentSignUpBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentWelcomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: FragmentWelcomeBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        val tvSignUp = requireActivity().findViewById(R.id.tv_sign_up) as TextView
        val tvLogin = requireActivity().findViewById(R.id.tv_login) as TextView
        val tvWelcome = requireActivity().findViewById(R.id.tv_welcome) as TextView

        binding.btnWelcomeSignup.setOnClickListener {
            tvSignUp.background = resources.getDrawable(R.drawable.textlines, requireActivity().baseContext.theme)
            tvLogin.setBackgroundResource(0)
            tvWelcome.setBackgroundResource(0)
            (requireActivity() as MainActivity).loadFragment(SignUpFragment())
        }

        binding.btnWelcomeLogin.setOnClickListener {
            tvLogin.background = resources.getDrawable(R.drawable.textlines, requireActivity().baseContext.theme)
            tvSignUp.setBackgroundResource(0)
            tvWelcome.setBackgroundResource(0)
            (requireActivity() as MainActivity).loadFragment(LoginFragment())
        }




        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}