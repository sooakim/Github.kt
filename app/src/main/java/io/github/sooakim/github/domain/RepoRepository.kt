package io.github.sooakim.github.domain

import io.github.sooakim.github.data.model.PaginatedResponse
import io.github.sooakim.github.data.model.RepoResponse

/**
 * Created by sooakim on 06,June,2022
 */
interface RepoRepository {
    suspend fun fetchRepos(search: String): PaginatedResponse<RepoResponse>
}