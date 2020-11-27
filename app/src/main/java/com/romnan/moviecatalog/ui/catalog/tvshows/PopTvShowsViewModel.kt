package com.romnan.moviecatalog.ui.catalog.tvshows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.api.ApiConfig
import com.romnan.moviecatalog.api.ApiService
import com.romnan.moviecatalog.model.PopShow
import com.romnan.moviecatalog.model.PopShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopTvShowsViewModel : ViewModel() {
    private val _popTvShows = MutableLiveData<List<PopShow>>()
    val popTvShows: LiveData<List<PopShow>> = _popTvShows

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "PopTvShowsViewModel"
    }

    fun getPopTvShows() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularTvShows(ApiService.API_KEY)

        client.enqueue(object : Callback<PopShowResponse> {
            override fun onResponse(
                call: Call<PopShowResponse>,
                response: Response<PopShowResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popTvShows.value = response.body()?.results as List<PopShow>?
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopShowResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}