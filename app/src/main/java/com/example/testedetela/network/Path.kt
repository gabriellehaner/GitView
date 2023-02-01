package com.example.testedetela.network

import com.example.testedetela.model.ModelRepository
import com.example.testedetela.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Path {

        @GET("/users/{userName}")
        fun getUsers(
            @Path("userName")
            userName: String
        ) : Call<Users>

        @GET("/users/{userName}/repos")
        fun getRepos(
            @Path("userName")
            userName: String
        ) : Call<List<ModelRepository>>
    }
