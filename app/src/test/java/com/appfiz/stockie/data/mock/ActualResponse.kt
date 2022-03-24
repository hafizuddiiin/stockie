package com.appfiz.stockie.data.mock

import com.appfiz.stockie.data.response.market.Finance
import com.appfiz.stockie.data.response.market.GetPopularWatchlist
import com.appfiz.stockie.data.response.market.Portfolio
import com.appfiz.stockie.data.response.market.Section

val getPopularWatchlist = GetPopularWatchlist(
    finance = Finance(
        result = listOf(
            Section(
                id = "section-popular",
                name = "Most followed",
                total = 1,
                portfolios = listOf(
                    Portfolio(
                        userId = "X3NJ2A7VDSABUI4URBWME2PZNM",
                        pfId = "the_berkshire_hathaway_portfolio",
                        name = "The Berkshire Hathaway Portfolio",
                        shortDescription = "Follow this list to discover and track stocks held by Berkshire Hathaway, the holding company of Warren Buffett.",
                        followerCount = 211687,
                        createdAt = 1638489814695,
                        updatedAt = 1648080215478,
                        dailyPercentGain = -0.9741569625222395
                    )
                )
            )
        )
    )
)