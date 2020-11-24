package com.project.threeschool.model

import com.google.gson.annotations.SerializedName

data class Members (
    val statusCode : String,
    val data : MemberData
)

data class MemberData(
        val grade : Int,
        @SerializedName("class")val classNum : Int,
        val list : ArrayList<Double>
)

