package com.katsidzira.popular_movies.network

import com.katsidzira.popular_movies.data.ZodiacList
import io.reactivex.Observable
import retrofit2.http.GET

interface ZodiacService {

    @GET("/JDVila/storybook/master/zodiac.json")
    fun getEntireZodiac(): Observable<ZodiacList>

}