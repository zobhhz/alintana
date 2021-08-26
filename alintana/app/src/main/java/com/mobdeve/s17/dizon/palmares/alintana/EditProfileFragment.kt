package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentEditProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentUserProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : BaseProfileFragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = ACTIVITY.user

        ACTIVITY.title = "Edit Profile"


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        val sexList = listOf("Prefer Not To Say", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val sexAdapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_list, sexList)
        (binding.actvSex)?.setAdapter(sexAdapter)

        val prefList = listOf("Any", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val prefAdapter = ArrayAdapter(requireActivity().applicationContext, R.layout.item_list, prefList)
        (binding.actvPref)?.setAdapter(prefAdapter)

        binding.etUsername.setText(user.username)
        binding.etBirthdate.setText(user.birthdate)
        binding.actvSex.setText(sexAdapter.getItem(sexAdapter.getPosition(user.sex)), false)
        binding.actvPref.setText(prefAdapter.getItem(0), false)
        binding.etHeadline.setText(user.headline)
        binding.actvLoc.setText(user.location)
        binding.actvMobilenumber.setText(user.mobileNumber)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}