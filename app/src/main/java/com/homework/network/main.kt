package com.homework.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


data class RetrofitClassStatus(
    var verified: Boolean,
    var sendCount: Int
) {
    override fun toString(): String {
        return "Verified – $verified, Send count – $sendCount"
    }
}

data class RetrofitClassMain(
    var status: RetrofitClassStatus,
    var type: String,
    var deleted: Boolean,
    var text: String,
    var source: String,
    @SerializedName("createdAt")
    var createDate: String
) {
    override fun toString(): String {
        return "Status: $status\nType – $type\nDeleted — $deleted\nText – \"$text\"\nSource – $source\nCreate date – $createDate\n"
    }
}

interface RetrofitService {
    @GET("facts")
    fun getFacts(): Call<List<RetrofitClassMain>>
}

fun main() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val request = retrofit.create(RetrofitService::class.java)

    val call = request.getFacts()
    val variables = call.execute().body()

    variables?.forEach {
        println(it.toString())
    }
}
