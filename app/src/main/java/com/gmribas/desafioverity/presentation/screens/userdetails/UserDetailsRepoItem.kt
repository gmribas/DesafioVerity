package com.gmribas.desafioverity.presentation.screens.userdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmribas.desafioverity.R

@Composable
fun UserDetailsRepoItem(modifier: Modifier = Modifier, text: String, imageRes: Int, description: String?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.width(24.dp).height(24.dp),
            painter = painterResource(id = imageRes),
            contentDescription = description,
            tint = MaterialTheme.colorScheme.inversePrimary
        )
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
@Preview
fun PreviewUserDetailsRepoItem() {
    UserDetailsRepoItem(text = "text", imageRes = R.drawable.ic_git_repository, description = "")
}