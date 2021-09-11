package com.mobdeve.s17.dizon.palmares.alintana

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentSignUpBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.RegisterInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.mobdeve.s17.dizon.palmares.alintana.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    val calendar : Calendar = getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val items = listOf("Prefer Not To Say", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other")
        val sexAdapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        (binding.actvSex)?.setAdapter(sexAdapter)

        binding.actvSex.setText(sexAdapter.getItem(0), false)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "MM/dd/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.etBirthdate.setText(sdf.format(calendar.time))

        }
        binding.etUsername.setText("HarryPotter")
        binding.etBirthdate.setText("10/28/2000")
        binding.etHeadline.setText("Yer a Wizard")
        binding.actvMobilenumber.setText("09293397767")
        binding.actvLoc.setText("Hogwarts")
        binding.etPassword.setText("12345678")
        binding.etConfirmpassword.setText("12345678")


        binding.etBirthdate.setOnFocusChangeListener { v, hasFocus ->

            if(hasFocus){
                if (container != null) {
                    Log.i("SET", "HELLO")
                    DatePickerDialog(container.context, dateSetListener,
                        calendar.get(YEAR),
                        calendar.get(MONTH),
                        calendar.get(DAY_OF_MONTH))
                        .show()
                }
            }
        }
//
//        binding.etBirthdate.setOnClickListener {
//            Log.i("SET", "CHECK")
//
//            if (container != null) {
//                Log.i("SET", "HELLO")
//                DatePickerDialog(container.context, dateSetListener,
//                    calendar.get(YEAR),
//                    calendar.get(MONTH),
//                    calendar.get(DAY_OF_MONTH))
//                    .show()
//            }
//
//        }

        binding.btnSignup.setOnClickListener{

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmpassword.text.toString()
            val sex = binding.actvSex.text.toString()
            val location = binding.actvLoc.text.toString()
            val headline = binding.etHeadline.text.toString()
            val mobilenumber = binding.actvMobilenumber.text.toString()

            val birthdate = binding.etBirthdate.text.toString()

            if(username.isEmpty()){
                binding.etUsername.error = "Username required"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.etPassword.error = "Password required"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            if(confirmPassword.isEmpty()){
                binding.etConfirmpassword.error = "Password required"
                binding.etConfirmpassword.requestFocus()
                return@setOnClickListener
            }
            if(birthdate.isEmpty()){
                binding.etBirthdate.error = "Password required"
                binding.etBirthdate.requestFocus()
                return@setOnClickListener
            }

            val info = RegisterInformation(username,birthdate,sex,mobilenumber,location,headline,password,confirmPassword)

            APIClient.create().createUser(info).enqueue(object :
                Callback<RegisterResponse> {
                override fun onResponse(p0: Call<RegisterResponse>, p1: Response<RegisterResponse>) {

                    if(p1.body()?.status.equals("success")){
                        val user = p1.body()?.data!!

                        val gotoProfile : Intent = Intent(activity?.baseContext, ProfileActivity::class.java)
                        gotoProfile.putExtra("user", user)
                        startActivity(gotoProfile)

                    }else{
                        Toast.makeText(activity!!.applicationContext, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(p0: Call<RegisterResponse>, p1: Throwable) {
                    Toast.makeText(activity!!.applicationContext, p1.message, Toast.LENGTH_LONG).show()
                }
            })
        }

        return binding.root
    }

}