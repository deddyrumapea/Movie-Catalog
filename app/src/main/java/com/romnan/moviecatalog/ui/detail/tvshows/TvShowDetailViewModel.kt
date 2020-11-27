package com.romnan.moviecatalog.ui.detail.tvshows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.api.ApiConfig
import com.romnan.moviecatalog.api.ApiService
import com.romnan.moviecatalog.model.TvShowDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailViewModel : ViewModel() {
    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    val tvShowDetail: LiveData<TvShowDetail> = _tvShowDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "TvShowDetailViewModel"
    }

    fun getTvShowDetail(tvShowId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getTvShowDetail(tvShowId, ApiService.API_KEY, ApiService.VIDEOS)
        client.enqueue(object : Callback<TvShowDetail> {
            override fun onResponse(call: Call<TvShowDetail>, response: Response<TvShowDetail>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _tvShowDetail.value = response.body()
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}