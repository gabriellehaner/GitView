package com.example.testedetela.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkP {
    companion object {
        private const val URL_BASE: String = "https://api.github.com"
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}