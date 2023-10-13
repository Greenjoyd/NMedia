package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dao.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

private val empty = Post(
    id = 0,
    author = "",
    published = "",
    content = "",
    likedByMe = false,
    likes = 0,
    share = 0,
    views = 0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositorySQLiteImpl(AppDb.getInstance(application).postDao())

    val data= repository.getAll()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)




    fun cancelEdit(){
        edited.value = empty
    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }


    fun changeContent(content: String) {
        edited.value?.let { post ->
            if (content != post.content) {
                edited.value = post.copy(content = content)
            }
        }
    }
}