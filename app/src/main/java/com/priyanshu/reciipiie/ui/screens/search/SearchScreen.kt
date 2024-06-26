package com.priyanshu.reciipiie.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.screens.search.viewModels.SearchViewModel
import com.priyanshu.reciipiie.ui.theme.grey500
import com.priyanshu.reciipiie.ui.theme.lightGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {

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
                    .size(24.dp)
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon),
                tint = lightGrey
            )
            Spacer(modifier = Modifier.width(12.dp))
            BasicTextField(
                onValueChange = { viewModel.updateSearchQuery(it) },
                value = searchQuery,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = primaryColor),
                modifier = Modifier
                    .weight(5f)
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    //Handing search over here
                }),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search Recipes",
                            style = MaterialTheme.typography.bodyLarge.copy(color = lightGrey)
                        )
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(primaryColor)
            )
            if (searchQuery.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)
                        .padding(start = 16.dp, end = 8.dp)
                        .clickable {
                            viewModel.updateSearchQuery("")
                        },
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = lightGrey
                )
            }
        }
    }
}
