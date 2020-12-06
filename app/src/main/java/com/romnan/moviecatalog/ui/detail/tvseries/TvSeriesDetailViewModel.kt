package com.romnan.moviecatalog.ui.detail.tvseries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.api.ApiConfig
import com.romnan.moviecatalog.api.ApiService
import com.romnan.moviecatalog.model.TvSeriesDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvSeriesDetailViewModel : ViewModel() {
    private val _tvSeriesDetail = MutableLiveData<TvSeriesDetail>()
    val tvSeriesDetail: LiveData<TvSeriesDetail> = _tvSeriesDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "TvSeriesDetailViewModel"
    }

    fun getTvSeriesDetail(tvShowId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getTvSeriesDetail(tvShowId, ApiService.API_KEY, ApiService.VIDEOS)
        client.enqueue(object : Callback<TvSeriesDetail> {
            override fun onResponse(call: Call<TvSeriesDetail>, response: Response<TvSeriesDetail>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _tvSeriesDetail.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvSeriesDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}