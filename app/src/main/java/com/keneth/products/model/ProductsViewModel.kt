package com.keneth.products.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keneth.products.Api.RetrofitInstance
import com.keneth.products.data.ProductsItem
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val _productsState = mutableStateOf(ProductsState())
    val productsState: State<ProductsState>  = _productsState

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        _productsState.value = _productsState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()

                if (response.isSuccessful && response.body() != null) {
                    _productsState.value = _productsState.value.copy(
                        productsList = response.body()!!,
                        isLoading = false,
                        error = null
                    )
                } else {
                    _productsState.value = _productsState.value.copy(
                        isLoading = false,
                        error = "Failed to fetch products. Error: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _productsState.value = _productsState.value.copy(
                    isLoading = false,
                    error = "An error occurred: ${e.localizedMessage}"
                )
            }
        }
    }

    data class ProductsState(
        val productsList: List<ProductsItem> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
