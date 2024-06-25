package com.priyanshu.reciipiie.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.priyanshu.reciipiie.R
import com.priyanshu.reciipiie.ui.theme.blue
import com.priyanshu.reciipiie.ui.theme.grey300
import com.priyanshu.reciipiie.ui.theme.lightGrey
import com.priyanshu.reciipiie.ui.theme.primaryColor
import com.priyanshu.reciipiie.ui.theme.secondaryColor
import com.priyanshu.reciipiie.utils.AppUtils

@Composable
fun TopAppBar(
    userName: String,
    profilePicUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .border(width = 1.dp, color = grey300, shape = CircleShape),
                model = profilePicUrl,
                placeholder = painterResource(id = R.drawable.sample_profile_img),
                error = painterResource(id = R.drawable.sample_profile_img),
                contentDescription = "Profile pic"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = AppUtils.getGreetText(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = userName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(shape = CircleShape, color = Color.Transparent)
                .border(width = 1.dp, shape = CircleShape, color = grey300)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = " Notification",
            tint = primaryColor
        )
    }
}