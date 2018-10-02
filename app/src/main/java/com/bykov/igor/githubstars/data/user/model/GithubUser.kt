package com.bykov.igor.githubstars.data.user.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String
)