package com.bykov.igor.githubstars.data.user.model

data class GithubRepsonse(
    val total_count: Long,
    val items: List<GithubUser>
)