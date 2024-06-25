package com.priyanshu.reciipiie.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.screens.search.viewModels.SearchViewModel
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey

@Composable
fun SearchScreen(
    viewModel: SearchViewModel
) {
    val searchQuery by viewModel.searchQuery

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(grey500),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 16.dp, end = 8.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon),
                tint = lightGrey
            )
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                onValueChange = {
                    viewModel.updateSearchQuery(it)
                },
                value = searchQuery,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = lightGrey)
            )
        }

    }
}