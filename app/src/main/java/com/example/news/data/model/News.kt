package com.example.news.data.model

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("title")
    var title : String?="",

    @SerializedName("description")
    var description: String?="",

    @SerializedName("source")
    var source: String?="",

    @SerializedName("url")
    var url : String?="",

    @SerializedName("imageUrl")
    var imageUrl : String=""

){

}
