package ru.netology.nmedia.dto

class Post  (
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean = false,
    var likes: Int = 0,
    var share: Int = 1199,
    var views: Int = 5,
)