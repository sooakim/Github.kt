package io.github.sooakim.github.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sooakim on 06,June,2022
 */

data class PaginatedResponse<Item>(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<Item>
)