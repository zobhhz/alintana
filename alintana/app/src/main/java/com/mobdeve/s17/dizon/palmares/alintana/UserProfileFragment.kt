package com.mobdeve.s17.dizon.palmares.alintana

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentUserProfileBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseProfileFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : BaseProfileFragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User
    var client = APIClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        client.getUserById(user._id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                ACTIVITY.user = response.body()!!
                user = ACTIVITY.user
                bindUserData()
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        user = ACTIVITY.user

        ACTIVITY.title = "My Profile"

        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        bindUserData()
        binding.btnProfileMatch.setOnClickListener {
            (requireActivity() as ProfileActivity).loadFragment(MatchFragment())
            ACTIVITY.binding.navView.setCheckedItem(R.id.menu_find_match)
        }

        binding.btnProfileEdit.setOnClickListener {
            (requireActivity() as ProfileActivity).loadFragment(EditProfileFragment())
            ACTIVITY.binding.navView.setCheckedItem(R.id.menu_editprofile)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    fun bindUserData(){
        binding.tvProfileUsername.text = user.username
        binding.tvProfileHeadline.text = user.headline

        if(user.userImage != null && !TextUtils.isEmpty(user.userImage)){
            Picasso.get().load(user.userImage).into(binding.ivProfileImage)
        }
        else {
            binding.ivProfileImage.setImageResource(R.drawable.ic_baseline_person_24)
        }

        binding.tvProfileAge.text = user.getAge().toString()
        binding.tvProfileSex.text =  user.sex
        binding.tvProfileLoc.text =  user.location

        if(user.sex.equals("Prefer Not to Say", true)){
            binding.tvProfileSep1.visibility = View.GONE
            binding.tvProfileSex.visibility = View.GONE
        }
        if(TextUtils.isEmpty(user.location)){
            binding.tvProfileSep2.visibility = View.GONE
            binding.tvProfileLoc.visibility = View.GONE
        }

        binding.tvProfileLvl.text = (user.experience/100).toString()
        binding.progressBar.progress = user.experience%100
        binding.progressBar.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.primary))

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}