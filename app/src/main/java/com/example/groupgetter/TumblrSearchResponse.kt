package com.example.groupgetter

data class TumblrSearchResponse(
        val meta: Meta?,
        val response: List<TumblrPost>
)

data class Meta(
        val status: Int,
        val msg: String
)

data class TumblrPost(
        val blog_name: String,
        val summary: String?,
        val post: String,
        val type: String?,
        val pictures: List<TumblrPicture>?,
        val trail: List<TrailItem>?
)

data class TumblrPicture(
        val original_size: TumblrPictureSize
)

data class TumblrPictureSize(
        val url: String
)

data class TrailItem(
        val blog: BlogData,
        val content: String?
)

data class BlogData(
        val name: String
)
