package com.example.simpleuserprofilefetcher


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileRepository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun fetchUserProfile(callback: (UserProfile?) -> Unit) {
        apiService.getUserProfile().enqueue(object : retrofit2.Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: retrofit2.Response<UserProfile>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                callback(null)
            }
        })
    }
}
