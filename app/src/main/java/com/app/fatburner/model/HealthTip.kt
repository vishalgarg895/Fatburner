package com.app.fatburner.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HealthTip {
    val EMPTY = ""

    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("title")
    @Expose
    val title: String = EMPTY

    @SerializedName("description")
    @Expose
    val description: String = EMPTY

    @SerializedName("image")
    @Expose
    val image: String = EMPTY
}