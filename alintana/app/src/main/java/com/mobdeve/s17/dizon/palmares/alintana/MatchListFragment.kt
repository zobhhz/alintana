package com.mobdeve.s17.dizon.palmares.alintana

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s17.dizon.palmares.alintana.adapter.MatchAdapter
import com.mobdeve.s17.dizon.palmares.alintana.adapter.MyMatchesAdapter
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentMatchBinding
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentMatchListBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseProfileFragment
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.mobdeve.s17.dizon.palmares.alintana.model.response.MatchesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchListFragment : BaseProfileFragment() {
    private var _binding: FragmentMatchListBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : User
    private var matches : ArrayList<User> = ArrayList()
    private lateinit var myMatchAdapter: MyMatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = ACTIVITY.user
        ACTIVITY.title = "My Matches"
        _binding = FragmentMatchListBinding.inflate(inflater, container, false)

        myMatchAdapter = MyMatchesAdapter(requireActivity().applicationContext, matches, user)
        binding.rvMyMatches.layoutManager = LinearLayoutManager(requireActivity().applicationContext,LinearLayoutManager.VERTICAL, false)
        binding.rvMyMatches.adapter = myMatchAdapter

        loadData()
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadData(){
        var tempMatches : ArrayList<User> = ArrayList()
        APIClient.create().getMyMatches(user._id).enqueue(object :
            Callback<MatchesResponse> {
            override fun onResponse(
                call: Call<MatchesResponse>,
                response: Response<MatchesResponse>
            ) {
                tempMatches = response.body()!!.matches
                myMatchAdapter.setList(tempMatches)
            }
            override fun onFailure(call: Call<MatchesResponse>, t: Throwable) {
            }
        })

    }

}