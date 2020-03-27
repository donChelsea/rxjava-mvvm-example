package com.katsidzira.popular_movies.data


import com.google.gson.annotations.SerializedName

data class ZodiacList(
    @SerializedName("zodiac")
    val zodiac: List<Zodiac>
)