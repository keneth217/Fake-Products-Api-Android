package com.keneth.products.model

import androidx.annotation.OptIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.keneth.products.Api.DummyJsonRetrofitInstance

import com.keneth.products.data.Juice.FruitsResponse

import kotlinx.coroutines.launch

class JuiceViewModel : ViewModel() {
    private val _juiceState = mutableStateOf(JuiceState())
    val juiceState: State<JuiceState> = _juiceState

    init {
        fetchJuice()
    }

    @OptIn(UnstableApi::class)
    fun fetchJuice() {
        _juiceState.value = _juiceState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val response = DummyJsonRetrofitInstance.dummyJsonResults.getProducts()
                if (response.products.isNotEmpty()) {
                    _juiceState.value = _juiceState.value.copy(
                        juiceList = response,
                        isLoading = false,
                        error = null
                    )
                    Log.d("JuiceViewModel", "Products fetched successfully")
                    Log.d("JuiceViewModel", "Products: ${response.products}")
                    Log.d("JuiceViewModel", "Total: ${response.total}")
                    Log.d("JuiceViewModel", "Limit: ${response.limit}")
                    Log.d("JuiceViewModel", "Skip: ${response.skip}")
                } else {
                    _juiceState.value = _juiceState.value.copy(
                        isLoading = false,
                        error = "No products available"
                    )
                    Log.e("JuiceViewModel", "No products found.")
                }
            } catch (e: Exception) {
                _juiceState.value = _juiceState.value.copy(
                    isLoading = false,
                    error = "An error occurred: ${e.localizedMessage}"
                )
                Log.e("JuiceViewModel", "Failed to fetch products. Error: ${e.localizedMessage}")
            }
        }
    }


    data class JuiceState(
        val juiceList: FruitsResponse = FruitsResponse(0, emptyList(), 0, 0),
        val isLoading: Boolean = false,
        val error: String? = null
    )


}