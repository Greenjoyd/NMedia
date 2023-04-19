package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

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

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()

    val data: LiveData<List<Post>> = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)

     val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post:Post){
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