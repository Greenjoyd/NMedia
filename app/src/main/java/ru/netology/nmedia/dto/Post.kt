package ru.netology.nmedia.dto

import android.provider.MediaStore.Video

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = false,
    val likes: Int = 0,
    var share: Int = 0,
    val views: Int = 0,
    var video: Boolean = false
)