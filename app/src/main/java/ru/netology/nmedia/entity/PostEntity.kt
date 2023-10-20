package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean = false,
    val likes: Int = 0,
    var share: Int = 0,
    val views: Int = 0,
    var video: String  = ""
) {
    fun toDto() = Post(id,author,published,content,likedByMe,likes,share)


    companion object{
        fun fromDto(post: Post)= with(post){
            PostEntity(id, author, published, content, likedByMe, likes, share)
        }

    }


}