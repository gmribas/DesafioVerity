package com.gmribas.desafioverity.presentation.screens.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gmribas.desafioverity.R
import com.gmribas.desafioverity.presentation.common.ObserveLifecycle
import com.gmribas.desafioverity.presentation.common.UiState
import com.gmribas.desafioverity.presentation.components.CircularLoadingCenter
import com.gmribas.desafioverity.presentation.components.DialogLoadingError
import com.gmribas.desafioverity.presentation.model.UserUIModel
import com.gmribas.desafioverity.presentation.navigation.Screens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = koinViewModel()
) {
    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

    val state = viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.user_list_screen_name),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                when (state.value) {
                    is UiState.Loading -> {
                        CircularLoadingCenter()
                    }

                    is UiState.Success -> {
                        UsersList(
                            navController,
                            state.value as UiState.Success<List<UserUIModel>>
                        )
                    }

                    is UiState.Error -> DialogLoadingError(errorMessage = (state.value as UiState.Error).error) {
                        viewModel.dismissErrorDialog()
                    }

                    is UiState.Default -> {}
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewContactListScreen() {
    UserListScreen(navController = rememberNavController())
}

@Composable
private fun UsersList(
    navController: NavHostController,
    state: UiState.Success<List<UserUIModel>>
) {
    LazyColumn {
        items(state.data.size) { index ->
            UserItem(user = state.data[index]) { clickedUser ->
                navController.navigate(Screens.UserDetails.route + "/${clickedUser.login}")
            }

            if (index < state.data.size) {
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}