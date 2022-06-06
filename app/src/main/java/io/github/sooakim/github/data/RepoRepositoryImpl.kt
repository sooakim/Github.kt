package io.github.sooakim.github.data

import android.net.Network
import io.github.sooakim.github.data.model.PaginatedResponse
import io.github.sooakim.github.data.model.RepoResponse
import io.github.sooakim.github.data.util.NetworkService
import io.github.sooakim.github.domain.RepoRepository
import javax.inject.Inject

/**
 * Created by sooakim on 06,June,2022
 */
class RepoRepositoryImpl @Inject constructor(): RepoRepository{
    private val repoApi = NetworkService.instance.repoApi

    override suspend fun fetchRepos(search: String): PaginatedResponse<RepoResponse> {
        return repoApi.searchRepos(query = search, sort = "stars")
    }
}