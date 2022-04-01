package com.appfiz.stockie.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appfiz.stockie.data.repository.MarketRepository
import com.appfiz.stockie.data.repository.MarketRepositoryImpl
import com.appfiz.stockie.data.response.Response
import com.appfiz.stockie.data.response.market.Portfolio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularViewModel(private val marketRepository: MarketRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(PopularViewState())
    val state: StateFlow<PopularViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val popularWatchlistResponse = marketRepository.getPopularWatchlistResponse()
            _state.update {
                when (popularWatchlistResponse) {
                    is Response.Success -> {
                        val portfolios = popularWatchlistResponse.data.finance.result[0].portfolios
                        it.copy(portfolios = portfolios)
                    }
                    else -> { it.copy(portfolios = emptyList())}
                }
            }
        }
    }
}

data class PopularViewState(
    val portfolios: List<Portfolio> = emptyList()
)