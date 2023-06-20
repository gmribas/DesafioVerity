package com.gmribas.desafioverity.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gmribas.desafioverity.R

@Composable
fun DialogLoadingError(errorMessage: String, okButtonAction: () -> Unit) {
    AlertDialog(
        title = { Text(text = stringResource(id = R.string.user_list_screen_error_title)) },
        text = { Text(text = stringResource(id = R.string.user_list_screen_error_message) + " $errorMessage") },
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = {
                okButtonAction()
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}