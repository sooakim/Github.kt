package io.github.sooakim.github.data.api

import io.github.sooakim.github.data.model.PaginatedResponse
import io.github.sooakim.github.data.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sooakim on 06,June,2022
 */
interface RepoApi {
    @GET("/search/repositories")
    suspend fun searchRepos(
        @Query("q")
        query: String,

        @Query("sort")
        sort: String,

        @Query("order")
        order: String = "desc",

        @Query("per_page")
        perPage: Int = 30,

        @Query("page")
        page: Int = 1
    ): PaginatedResponse<RepoResponse>
}