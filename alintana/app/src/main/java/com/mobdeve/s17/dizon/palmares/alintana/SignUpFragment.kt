package com.mobdeve.s17.dizon.palmares.alintana

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentSignUpBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "MM/dd/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.etBirthdate.setText(sdf.format(calendar.time))

        }


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


        return binding.root
    }

}