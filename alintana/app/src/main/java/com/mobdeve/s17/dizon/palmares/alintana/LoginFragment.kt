package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentLoginBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.LoginInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.response.LoginResponse
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var apiClient : APIClient
    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: FragmentLoginBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener{

            val username = binding.etLoginUsername.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()

            if(username.isEmpty()){
                binding.etLoginUsername.error = "Username required"
                binding.etLoginUsername.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.etLoginPassword.error = "Password required"
                binding.etLoginPassword.requestFocus()
                return@setOnClickListener
            }

            APIClient.create().loginUser(LoginInformation(username, password)).enqueue(object :
                Callback<LoginResponse> {
                override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                    if(p1.body()?.status.equals("success")){
                        Toast.makeText(activity!!.applicationContext, p1.body()?.message.toString() + " "+ p1.body()?.data?.username, Toast.LENGTH_LONG).show()
                        val rawData = p1.body()?.data!!
                        val user = User(rawData._id, rawData.username, rawData.birthdate, rawData.sex, rawData.mobileNumber, rawData.location, rawData.headline, rawData.experience, rawData.createdAt, rawData.userImage)

                        val gotoProfile : Intent = Intent(activity?.baseContext, ProfileActivity::class.java)
//                        val gotoEditProfile: Intent  = Intent(activity?.baseContext, EditProfileActivity::class.java)

                        gotoProfile.putExtra("user", user)
//                        gotoEditProfile.putExtra("user", user)
                        startActivity(gotoProfile)

                    }else{
                        Toast.makeText(activity!!.applicationContext, "Error: Wrong Username or Password", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                    Toast.makeText(activity!!.applicationContext, p1.message, Toast.LENGTH_LONG).show()
                }
            })
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}