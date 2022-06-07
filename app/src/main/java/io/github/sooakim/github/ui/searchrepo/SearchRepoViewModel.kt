package io.github.sooakim.github.ui.searchrepo

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sooakim.github.data.model.RepoResponse
import io.github.sooakim.github.domain.RepoRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject

/**
 * Created by sooakim on 06,June,2022
 */
@HiltViewModel
class SearchRepoViewModel @Inject constructor(
    private val repoRepository: RepoRepository
): ViewModel(){
    private val _state = MutableStateFlow(createInitialState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state
                .distinctUntilChangedBy { it.searchText }
                .filter { it.searchText.isNotBlank() }
                .debounce(700)
                .map { repoRepository.fetchRepos(it.searchText).items }
                .collect{
                   update { copy(repos = it) }
                }
        }
    }

    fun setSearchText(searchText: String) {
        update{
            copy(searchText = searchText)
        }
    }

    private fun update(action: State.() -> State){
        _state.value = action.invoke(_state.value)
    }

    private fun createInitialState(): State{
        return State()
    }

    data class State(
        val repos: List<RepoResponse> = emptyList(),
        val searchText: String = ""
    )
}