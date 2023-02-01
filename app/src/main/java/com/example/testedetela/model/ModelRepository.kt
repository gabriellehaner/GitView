package com.example.testedetela.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelRepository(
    @SerializedName("name")
    var name: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("html_url")
    var htmlUrl: String
) : Serializable
