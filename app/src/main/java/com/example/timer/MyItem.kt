package com.example.timer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class  MyItem(
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("author")
    var author: String,

    @Expose
    @SerializedName("avatar")
    var avatar: String,

    @Expose
    @SerializedName("description")
    var description: String,

    @Expose
    @SerializedName("language")
    var language: String,

    @Expose
    @SerializedName("stars")
    var stars: Int,


    @Expose
    @SerializedName("forks")
    var forks: Int

)
