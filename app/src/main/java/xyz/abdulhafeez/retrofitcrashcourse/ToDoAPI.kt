package xyz.abdulhafeez.retrofitcrashcourse

import retrofit2.Response
import retrofit2.http.GET

interface ToDoAPI {
    @GET("/todos")
    suspend fun getToDo():Response<List<DoDo>>
}