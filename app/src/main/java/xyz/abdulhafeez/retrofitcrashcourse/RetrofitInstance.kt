package xyz.abdulhafeez.retrofitcrashcourse

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val Api :ToDoAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoAPI::class.java)
    }
}