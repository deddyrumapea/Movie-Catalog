package com.romnan.moviecatalog.ui.discover.tvseries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.api.ApiConfig
import com.romnan.moviecatalog.api.ApiService
import com.romnan.moviecatalog.model.PopularShow
import com.romnan.moviecatalog.model.DiscoverResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverTvSeriesViewModel : ViewModel() {
    private val _popularTvSeries = MutableLiveData<List<PopularShow>>()
    val popularTvSeries: LiveData<List<PopularShow>> = _popularTvSeries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DiscoverTvSeriesViewModel"
    }

    fun getPopularTvSeries() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularTvSeries(ApiService.API_KEY)

        client.enqueue(object : Callback<DiscoverResponse> {
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popularTvSeries.value = response.body()?.results as List<PopularShow>?
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DiscoverResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}