package com.example.groupgetter
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

