package com.example.groupgetter

//data classes representing the structure of a subreddit search response
data class SubredditSearchResponse(
        val data: SubredditsInfo
)

data class SubredditsInfo(
        val children: List<SubredditChild>
)

data class SubredditChild(
        val data: SubredditInformation
)

data class SubredditInformation(
        val display_name: String,
        val title: String,
        val description: String,
        val icon: String,
)

