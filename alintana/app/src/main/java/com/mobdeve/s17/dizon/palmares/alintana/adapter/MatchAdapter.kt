package com.mobdeve.s17.dizon.palmares.alintana.adapter

import android.content.Context
import android.text.TextUtils.isEmpty
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
import com.mobdeve.s17.dizon.palmares.alintana.model.AddExperienceInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.AddMatchInformation
import com.mobdeve.s17.dizon.palmares.alintana.model.response.AddMatchResponse
import com.mobdeve.s17.dizon.palmares.alintana.model.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchAdapter(var context: Context, var matches: ArrayList<User>, var user: User): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun getItemCount(): Int{
        return matches.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.MatchViewHolder{
        return MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_card,parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.username.text = matches[position].username
        holder.headline.text = matches[position].headline

        if(matches[position].userImage != null && !isEmpty(matches[position].userImage))
            Picasso.get().load(matches[position].userImage).into(holder.userImg)
        else
            holder.userImg.setImageResource(R.drawable.ic_baseline_person_24)

        holder.age.text = matches[position].getAge().toString()
        holder.sex.text =  matches[position].sex
        holder.loc.text =  matches[position].location

        if(matches[position].sex.equals("Prefer Not to Say", true)){
            holder.separator1.visibility = View.GONE
            holder.sex.visibility = View.GONE
        }
        if(isEmpty(matches[position].location)){
            holder.loc.visibility = View.GONE
            holder.separator2.visibility = View.GONE
        }
    }

    fun setList(matches: ArrayList<User>){
        this.matches.clear()
        this.matches.addAll(matches)
        notifyDataSetChanged()
    }

    fun removeMatchCard(position: Int, type: Int){
        if(type == ItemTouchHelper.RIGHT){
            var client = APIClient.create()

            client.addMatch(AddMatchInformation(user._id, matches[position]._id)).enqueue(object : Callback<AddMatchResponse>{
                override fun onResponse(
                    call: Call<AddMatchResponse>,
                    response: Response<AddMatchResponse>
                ) {
                    Toast.makeText(context, "You liked " + matches[position].username, Toast.LENGTH_LONG).show()
                    matches.removeAt(position)
                    notifyItemRemoved(position);
                    client.addExperience(AddExperienceInformation(user._id, 50)).enqueue(object: Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            user.experience = response.body()!!.experience
                            Toast.makeText(context, "You gained 50xp", Toast.LENGTH_LONG).show()

                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }


                override fun onFailure(call: Call<AddMatchResponse>, t: Throwable) {

                }
            })
        }else{
            Toast.makeText(context, "You passed on " + matches[position].username, Toast.LENGTH_LONG).show()
            matches.removeAt(position)
            notifyItemRemoved(position);

        }

    }


    class MatchViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var userImg : ImageView
        var username : TextView
        var age : TextView
        var sex : TextView
        var loc : TextView
        var headline : TextView
        var separator1: TextView
        var separator2: TextView

        init{
            username = view.findViewById(R.id.tv_username)
            age = view.findViewById(R.id.tv_age)
            userImg = view.findViewById(R.id.siv_user)
            age= view.findViewById(R.id.tv_age)
            sex = view.findViewById(R.id.tv_sex)
            loc = view.findViewById(R.id.tv_loc)
            headline = view.findViewById(R.id.tv_headline)
            separator1 = view.findViewById(R.id.tv_sep1)
            separator2 = view.findViewById(R.id.tv_sep2)
        }


    }

}