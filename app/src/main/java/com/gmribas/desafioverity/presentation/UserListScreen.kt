package com.gmribas.desafioverity.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gmribas.desafioverity.R
import com.gmribas.desafioverity.presentation.common.UiState
import com.gmribas.desafioverity.presentation.navigation.Screens
import com.google.accompanist.permissions.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsState()

    val readContactsPermissionState = rememberPermissionState(
        android.Manifest.permission.READ_CONTACTS
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.user_list_screen_name),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
//                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.askForPermissionIfNeeded()
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) {

        when (state) {
            is UiState.Loading -> loading()
            is UiState.Success -> {}
            is UiState.Error -> {}
            is UiState.Default -> {}
            else -> {}
        }

        if (state.showDialogPermission) {
            if (readContactsPermissionState.status.isGranted) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.contact_list_screen_permission_granted_title),
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                DialogPermission(
                    viewModel = viewModel,
                    state = readContactsPermissionState,
                    rationale = readContactsPermissionState.status.shouldShowRationale
                )
            }
        }

        LazyColumn {
            items(state.contactList.size) { index ->
                UserItem(user = state.contactList[index]) { clickedUser ->
                    navController.navigate(Screens.UserDetails.route + "/${clickedUser.id}")
                }

                if (index < state.contactList.size) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
private fun loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun DialogPermission(
    viewModel: UserListViewModel,
    state: PermissionState,
    rationale: Boolean
) {
    val title = if (rationale) {
        stringResource(id = R.string.contact_list_screen_first_time_permission_title)
    } else {
        stringResource(id = R.string.contact_list_screen_rationale_permission_title)
    }

    AlertDialog(
        title = { Text(text = title) },
        onDismissRequest = { viewModel.dismissPermission() },
        confirmButton = {
            Button(onClick = {
                state.launchPermissionRequest()
                viewModel.dismissPermission()
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}

@Preview
@Composable
fun PreviewContactListScreen() {
    UserListScreen(navController = rememberNavController())
}