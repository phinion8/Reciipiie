package com.priyanshu.reciipiie.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.theme.grey
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey

@Composable
fun SearchBar(
    onSearchBarClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(grey500)
            .clickable {
                onSearchBarClick()
            },
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
        Text(
            text = stringResource(R.string.what_are_you_looking_for),
            style = MaterialTheme.typography.bodyLarge.copy(color = lightGrey)
        )

    }

}