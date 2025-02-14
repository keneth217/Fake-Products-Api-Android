package com.keneth.products.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keneth.products.Api.DummyJsonRetrofitInstance
import com.keneth.products.data.Quotes.QuotesResponse
import kotlinx.coroutines.launch

class QuotesViewModel : ViewModel() {
    private val _quotes = mutableStateOf(QuotesStates())
    val quotes: State<QuotesStates> = _quotes

    init {
        fetchQuotes()
    }

    fun fetchQuotes() {
        _quotes.value = _quotes.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val quotesResponse = DummyJsonRetrofitInstance.dummyJsonResults.getQuotes()

                if (quotesResponse.quotes.isNotEmpty()) {
                    _quotes.value = _quotes.value.copy(
                        quotesList = quotesResponse,
                        isLoading = false,
                        error = null
                    )
                } else {
                    _quotes.value = _quotes.value.copy(
                        isLoading = false,
                        error = "No quotes available"
                    )
                }
            } catch (
                e: Exception
            ) {
                _quotes.value = _quotes.value.copy(
                    isLoading = false,
                    error = "An error occurred: ${e.localizedMessage}"
                )
            }
        }
    }

    data class QuotesStates(

        val quotesList: QuotesResponse = QuotesResponse(0, emptyList(), 0, 0),
        val isLoading: Boolean = false,
        val error: String? = null
    )


}