package com.katsidzira.popular_movies.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.katsidzira.popular_movies.data.ZodiacList
import com.katsidzira.popular_movies.network.ZodiacService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class ZodiacDataSource(private val zodiacService: ZodiacService, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _zodiacList = MutableLiveData<ZodiacList>()
    val zodiacList: LiveData<ZodiacList>
        get() = _zodiacList

    fun fetchZodiacDetails(zodiacName: String) {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                zodiacService.getEntireZodiac()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _zodiacList.postValue(it)
                        _networkState.postValue(NetworkState.LOADING)
                    }, {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e(TAG, it.message)

                    })
            )
        } catch (e: Exception) {

        }

    }

    companion object {
        const val TAG = "ZodiacDataSource"
    }
}