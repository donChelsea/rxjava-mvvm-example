package com.katsidzira.popular_movies.repository

import android.arch.lifecycle.LiveData
import com.katsidzira.popular_movies.network.ZodiacService
import io.reactivex.disposables.CompositeDisposable

class ZodiacRepository(private val zodiacService: ZodiacService) {

    lateinit var zodiacDataSource: ZodiacDataSource

    fun fetchZodiacDetails(compositeDisposable: CompositeDisposable, zodiacName: String) {
        zodiacDataSource = ZodiacDataSource(zodiacService, compositeDisposable)
        zodiacDataSource.fetchZodiacDetails(zodiacName)
    }

    fun getZodiacNetworkState(): LiveData<NetworkState> {
        return zodiacDataSource.networkState
    }
}