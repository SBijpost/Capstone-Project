package com.sem.capstoneproject.repository

import android.util.Log
import com.sem.capstoneproject.model.SnepItem

class SnepRepository {

    private var sneps = listOf<SnepItem>()

    fun getSnepItems(uid: String, callback: ApiCallback) {

//        val request = ServiceBuilder.buildService(EndPoints::class.java)
//        val call = request.getMovies("fbfa2564113ded8c8f1b99d349f2a035", year, "en-US")
//
//        call.enqueue(object : Callback<MovieList> {
//            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
//                if (response.isSuccessful){
//                    movies = response.body()!!.results
//                    callback.onSuccess(movies)
//                }
//            }
//
//            override fun onFailure(call: Call<MovieList>, t: Throwable) {
//                Log.d("Error", t.message.toString())
//            }
//        })
    }

}

interface ApiCallback {
    fun onSuccess(result: List<SnepItem>)
}