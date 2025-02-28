package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Banner

@Composable
fun HomeBannersView(
    banners: List<Banner> = emptyList(),
) {
    when (banners.size) {
        0 -> EmptyBannerView()
        1 -> BannerCard(
            banner = banners.first()
        )
        else -> BannersCarrousel(banners = banners)
    }
}

@Composable
private fun EmptyBannerView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Transparent
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "🔥  KEEP IT UP  🔥")
        }
    }
}

@Composable
private fun BannersCarrousel(
    banners: List<Banner>
) {
    var minHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val pagerState = rememberPagerState(pageCount = { banners.size })

    Column {
        val pagerPadding =
            when (pagerState.currentPage) {
                0 -> PaddingValues(start = 10.dp, end = 25.dp)
                pagerState.pageCount - 1 -> PaddingValues(start = 25.dp, end = 10.dp)
                else -> PaddingValues(horizontal = 18.dp)
            }
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.wrapContentHeight(),
            contentPadding = pagerPadding,
            pageSpacing = 14.dp,
            beyondViewportPageCount = 3,
            state = pagerState
        ) { page ->
            BannerCard(
                modifier = Modifier
                    .heightIn(min = minHeight)
                    .onSizeChanged {
                        density.run {
                            val height = it.height.toDp()
                            if(height > minHeight) { minHeight = height }
                        }
                    },
                banner = banners[page]
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surface
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}