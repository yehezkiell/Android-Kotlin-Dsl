package com.example.abstraction.data


import com.google.gson.annotations.SerializedName

data class TeamApiResponse(
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("meta")
        val meta: Meta
) {
    data class Data(
            @SerializedName("abbreviation")
            val abbreviation: String,
            @SerializedName("city")
            val city: String,
            @SerializedName("conference")
            val conference: String,
            @SerializedName("division")
            val division: String,
            @SerializedName("full_name")
            val fullName: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
    )

    data class Meta(
            @SerializedName("current_page")
            val currentPage: Int,
            @SerializedName("next_page")
            val nextPage: Int,
            @SerializedName("per_page")
            val perPage: Int,
            @SerializedName("total_count")
            val totalCount: Int,
            @SerializedName("total_pages")
            val totalPages: Int
    )
}