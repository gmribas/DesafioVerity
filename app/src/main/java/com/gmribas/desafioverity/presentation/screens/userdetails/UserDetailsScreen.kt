package com.gmribas.desafioverity.presentation.screens.userdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.gmribas.desafioverity.R
import com.gmribas.desafioverity.presentation.common.ObserveLifecycle
import com.gmribas.desafioverity.presentation.common.UiState
import com.gmribas.desafioverity.presentation.components.CircularLoadingCenter
import com.gmribas.desafioverity.presentation.components.DialogLoadingError
import com.gmribas.desafioverity.presentation.model.UserDetailsUIModel
import com.gmribas.desafioverity.presentation.model.UserRepoUIModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(navController: NavController, viewModel: UserDetailsViewModel = koinViewModel()) {
    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

    val detailsState = viewModel.viewState.collectAsStateWithLifecycle()

    val reposState = viewModel.reposState.collectAsStateWithLifecycle()

    var userLogin by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${stringResource(R.string.user_details_screen_name)} $userLogin",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            when (detailsState.value) {
                is UiState.Loading -> {
                    CircularLoadingCenter()
                }

                is UiState.Success -> {
                    val details = (detailsState.value as UiState.Success<UserDetailsUIModel>).data
                    userLogin = details.login
                    Row(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                        UserDetails(userDetails = details)
                    }
                }

                is UiState.Error -> DialogLoadingError(errorMessage = (detailsState.value as UiState.Error).error) {

                }

                is UiState.Default -> {}
            }

            when (reposState.value) {
                is UiState.Loading -> {
                    CircularLoadingCenter()
                }

                is UiState.Success -> {
                    val repos = (reposState.value as UiState.Success<List<UserRepoUIModel>>).data
                    Row(modifier = Modifier.fillMaxWidth()) {
                        UserRepos(repos = repos)
                    }
                }

                is UiState.Error -> DialogLoadingError(errorMessage = (detailsState.value as UiState.Error).error) {

                }

                is UiState.Default -> {}
            }
        }
    }
}

@Composable
private fun UserDetails(userDetails: UserDetailsUIModel) {
    Column(
        modifier = Modifier
            .padding(top = 36.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(userDetails.avatarURL),
            contentDescription = userDetails.name,
            modifier = Modifier.size(128.dp).clip(RoundedCornerShape(64.dp))
        )
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
        Text(
            text = userDetails.name ?: "No name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier)
        Text(
            text = userDetails.location ?: userDetails.email ?: "",
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
private fun UserRepos(repos: List<UserRepoUIModel>) {
    Card {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .background(Color.White)
        ) {
            items(repos.size) { index ->
                val item = repos[index]
                val type = if (item.private) R.string.user_details_screen_repository_type_private else R.string.user_details_screen_repository_type_public
                val formattedDescription = String.format(stringResource(R.string.user_details_screen_repository_type), stringResource(id = type))

                val description by remember {
                    mutableStateOf(formattedDescription)
                }

                UserDetailsRepoItem(
                    text = item.fullName,
                    imageRes = R.drawable.ic_git_repository,
                    description = description
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserDetailsScreen() {
    UserDetailsScreen(rememberNavController())
}