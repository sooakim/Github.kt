package io.github.sooakim.github.ui.searchrepo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sooakim.github.ui.component.GHTextField
import io.github.sooakim.github.ui.theme.GithubktTheme
import kotlin.coroutines.CoroutineContext

/**
 * Created by sooakim on 06,June,2022
 */
@Composable
fun SearchRepoView(
    searchRepoViewModel: SearchRepoViewModel = viewModel()
){
    val state = searchRepoViewModel.state.collectAsState().value

    GithubktTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
                    then Modifier.windowInsetsPadding(WindowInsets.systemBars.union(WindowInsets.ime)),
            color = MaterialTheme.colors.background
        ) {
            Column {
                GHTextField(
                    value = state.searchText,
                    onValueChange = { searchRepoViewModel.setSearchText(it) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "검색",
                            tint = MaterialTheme.colors.primary
                        )
                    },
                    singleLine = true,
                )
                LazyColumn{
                    items(state.repos, key = { it.id }){
                        Text("${it.name}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRepoView_Preview() {
    SearchRepoView()
}