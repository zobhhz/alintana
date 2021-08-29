package com.mobdeve.s17.dizon.palmares.alintana.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s17.dizon.palmares.alintana.R
import com.mobdeve.s17.dizon.palmares.alintana.api.APIClient
import com.mobdeve.s17.dizon.palmares.alintana.model.AddMatchInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.mobdeve.s17.dizon.palmares.alintana.model.response.AddMatchResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyMatchesAdapter (var context: Context, var matches: ArrayList<User>, var user: User): RecyclerView.Adapter<MyMatchesAdapter.MyMatchesViewHolder>() {

    override fun getItemCount(): Int{
        return matches.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchesAdapter.MyMatchesViewHolder{
        return MyMatchesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match,parent, false))
    }

    override fun onBindViewHolder(holder: MyMatchesViewHolder, position: Int) {
        holder.username.text = matches[position].username

        if(matches[position].userImage != null && !TextUtils.isEmpty(matches[position].userImage))
            Picasso.get().load(matches[position].userImage).into(holder.userImg)
        else
            holder.userImg.setImageResource(R.drawable.ic_baseline_person_24)

        holder.age.text = matches[position].getAge().toString()
        holder.sex.text =  matches[position].sex
        holder.loc.text =  matches[position].location

        if(TextUtils.isEmpty(matches[position].location)){
            holder.loc.visibility = View.GONE
        }

        if(matches[position].sex.equals("Prefer Not to Say", true)){
            holder.separator1.visibility = View.GONE
            holder.sex.visibility = View.GONE
        }
        if(TextUtils.isEmpty(matches[position].location)){
            holder.loc.visibility = View.GONE
            holder.separator2.visibility = View.GONE
        }

    }

    fun setList(matches: ArrayList<User>){
        this.matches.clear()
        this.matches.addAll(matches)
        notifyDataSetChanged()
    }



    class MyMatchesViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var userImg : ImageView
        var username : TextView
        var age : TextView
        var sex : TextView
        var loc : TextView
        var separator1: TextView
        var separator2: TextView

        init{
            username = view.findViewById(R.id.tv_username)
            age = view.findViewById(R.id.tv_age)
            userImg = view.findViewById(R.id.siv_user)
            age= view.findViewById(R.id.tv_age)
            sex = view.findViewById(R.id.tv_sex)
            loc = view.findViewById(R.id.tv_loc)
            separator1 = view.findViewById(R.id.tv_sep1)
            separator2 = view.findViewById(R.id.tv_sep2)
        }
    }
}