package com.mobdeve.s17.dizon.palmares.alintana

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s17.dizon.palmares.alintana.adapter.MatchAdapter
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.databinding.ActivityMatchBinding
import com.mobdeve.s17.dizon.palmares.alintana.model.PossibleMatchesResponse
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMatchBinding
    private var matches : ArrayList<User> = ArrayList()
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)

        matchAdapter = MatchAdapter(applicationContext, matches)
        binding.rvPossibleMatches.layoutManager = NoScrollHorizontalLayoutManager(applicationContext)
        binding.rvPossibleMatches.adapter = matchAdapter
        setContentView(binding.root)
        loadData()

    }

    class NoScrollHorizontalLayoutManager(context: Context) : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
        override fun canScrollHorizontally(): Boolean {
            return false
        }
    }


        fun loadData(){
        var tempMatches : ArrayList<User> = ArrayList()
        APIClient.create().getPossibleMatches("611f3b15c7fff46954aa218e").enqueue(object :
            Callback<PossibleMatchesResponse>{
            override fun onResponse(
                call: Call<PossibleMatchesResponse>,
                response: Response<PossibleMatchesResponse>
            ) {
                tempMatches = response.body()!!.matches
                matchAdapter.setList(tempMatches)
            }

            override fun onFailure(call: Call<PossibleMatchesResponse>, t: Throwable) {

            }


        })

    }
}