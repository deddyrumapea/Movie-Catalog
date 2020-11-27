package com.romnan.moviecatalog.ui.catalog.movies

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

class PopMoviesViewModel : ViewModel() {
    private val _popMovies = MutableLiveData<List<PopShow>>()
    val popMovies: LiveData<List<PopShow>> = _popMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "PopMoviesViewModel"
    }

    fun getPopMovies() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPopularMovies(ApiService.API_KEY)

        client.enqueue(object : Callback<PopShowResponse> {
            override fun onResponse(
                call: Call<PopShowResponse>,
                response: Response<PopShowResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popMovies.value = response.body()?.results as List<PopShow>?
                    Log.d(TAG, "onResponse: ")
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