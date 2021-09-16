package com.mobdeve.s17.dizon.palmares.alintana.api

import com.mobdeve.s17.dizon.palmares.alintana.model.*
import com.mobdeve.s17.dizon.palmares.alintana.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {


    @POST("/register")
    fun createUser(@Body registerInformation: RegisterInformation): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginInformation: LoginInformation) : Call<LoginResponse>

    @GET("api/v1/user/{id}")
    fun getUserById(@Path("id") name : String): Call<User>

    @GET("api/v1/match/possible/{id}")
    fun getPossibleMatches(@Path("id") id : String): Call<MatchesResponse>

    @GET("api/v1/match/{id}")
    fun getMyMatches(@Path("id") id : String) : Call<MatchesResponse>

    @POST("api/v1/user/exp/add")
    fun addExperience(@Body addExperienceInformation: AddExperienceInformation) : Call<User>

    @POST("api/v1/match/add")
    fun addMatch(@Body addMatchInformation: AddMatchInformation) : Call<AddMatchResponse>

    @POST("api/v1/match/ignore")
    fun ignoreMatch(@Body addMatchInformation: AddMatchInformation) : Call<AddMatchResponse>

    @PUT("api/v1/user")
    fun updateUser(@Body updateUserInformation: UpdateUserInformation) : Call<User>

    @PUT("api/v1/user/updatePassword")
    fun updatePassword(@Body updatePasswordInformation: UpdatePasswordInformation) : Call<User>

    @GET("api/v1/quiz/{category}")
    fun getQuiz(@Path("category") category: String) : Call<Quiz>

    @POST("api/v1/quiz/")
    fun addQuizResult(@Body addQuizResultInformation: AddQuizResultInformation) : Call<QuizResult>

    @GET("api/v1/quiz/{user}/{quiz}")
    fun getQuiz(@Path("user") user: String, @Path("quiz") quiz: String) : Call<Leaderboard>
}