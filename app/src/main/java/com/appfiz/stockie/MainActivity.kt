package com.appfiz.stockie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.appfiz.stockie.ui.theme.StockieTheme
import com.appfiz.stockie.ui.watchlist.PopularViewModel
import com.appfiz.stockie.ui.watchlist.PopularWatchlistScreen
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockieTheme {
                val viewModel: PopularViewModel by viewModel()
                PopularWatchlistScreen(viewModel = viewModel)
            }
        }
    }
}