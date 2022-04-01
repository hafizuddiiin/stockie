package com.appfiz.stockie.ui.watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.appfiz.stockie.R
import com.appfiz.stockie.data.response.market.Portfolio
import com.appfiz.stockie.data.response.market.previewPortfolio
import com.appfiz.stockie.ui.theme.StockieTheme

@Composable
fun PopularWatchlistScreen(
    viewModel: PopularViewModel
) {
    val viewState by viewModel.state.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        PortfolioRow(portfolios = viewState.portfolios)
    }
}

@Composable
fun PortfolioRow(
    portfolios: List<Portfolio>
) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            text = stringResource(R.string.popular_watchlist_row_title),
            style = MaterialTheme.typography.subtitle1
        )
        val lastIndex = portfolios.size - 1
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
        ) {
            itemsIndexed(portfolios) {
                    index: Int, portfolio: Portfolio ->
                PortfolioItem(portfolio = portfolio)

                if (index < lastIndex) Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}

@Composable
fun PortfolioItem(portfolio: Portfolio) {
    
    Column(
        Modifier.width(128.dp)
    ) {
        AsyncImage(
            model = portfolio.backgroundImage?.sizeMedium?.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = portfolio.name,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview("Default portfolio item")
@Composable
fun PreviewPortfolioItem() {
    StockieTheme {
        Surface {
            PortfolioItem(portfolio = previewPortfolio)
        }
    }
}