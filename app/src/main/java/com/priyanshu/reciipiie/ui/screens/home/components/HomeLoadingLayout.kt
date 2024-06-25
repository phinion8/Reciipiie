package com.priyanshu.reciipiie.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.priyanshu.reciipiie.ui.components.shimmerLoadingAnimation

@Composable
fun HomeLoadingLayout() {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerLoadingAnimation()
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(16.dp))
                        .height(180.dp)
                        .shimmerLoadingAnimation()
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth(0.5f)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerLoadingAnimation()
                )
            }
            items(5) {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerLoadingAnimation()
                )
            }
        }
}