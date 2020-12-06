package com.romnan.moviecatalog.ui.discover.movies

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

class DiscoverMovieViewModel : ViewModel() {
    private val _popularMovies = MutableLiveData<List<PopularShow>>()
    val popularMovies: LiveData<List<PopularShow>> = _popularMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DiscoverMovieViewModel"
    }

    fun getPopularMovies() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularMovies(ApiService.API_KEY)

        client.enqueue(object : Callback<DiscoverResponse> {
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popularMovies.value = response.body()?.results as List<PopularShow>?
                    Log.d(TAG, "onResponse: ")
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