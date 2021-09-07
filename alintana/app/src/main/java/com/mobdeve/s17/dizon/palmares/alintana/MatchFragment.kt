package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s17.dizon.palmares.alintana.adapter.MatchAdapter
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.FragmentMatchBinding
import com.mobdeve.s17.dizon.palmares.alintana.helpers.BaseProfileFragment
import com.mobdeve.s17.dizon.palmares.alintana.helpers.SwipeCallback
import com.mobdeve.s17.dizon.palmares.alintana.model.response.MatchesResponse
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchFragment : BaseProfileFragment()  {
    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!
    private var matches : ArrayList<User> = ArrayList()
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var itemTouchHelper : ItemTouchHelper
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = ACTIVITY.user
        ACTIVITY.title = "Find a Match"
        _binding = FragmentMatchBinding.inflate(inflater, container, false)

        matchAdapter = MatchAdapter(requireActivity().applicationContext, matches, user, binding.tvMatchNotif)
        binding.rvPossibleMatches.layoutManager = NoScrollHorizontalLayoutManager(requireActivity().applicationContext)
        binding.rvPossibleMatches.adapter = matchAdapter

        itemTouchHelper = ItemTouchHelper(SwipeCallback(matchAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rvPossibleMatches)

        loadData()
        return binding.root
    }

    class NoScrollHorizontalLayoutManager(context: Context) : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
        override fun canScrollHorizontally(): Boolean {
            return false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
        var tempMatches : ArrayList<User> = ArrayList()
        APIClient.create().getPossibleMatches(user._id).enqueue(object :
            Callback<MatchesResponse> {
            override fun onResponse(
                call: Call<MatchesResponse>,
                response: Response<MatchesResponse>
            ) {
                tempMatches = response.body()!!.matches
                matchAdapter.setList(tempMatches)
            }

            override fun onFailure(call: Call<MatchesResponse>, t: Throwable) {
            }
        })
    }


}