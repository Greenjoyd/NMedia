package ru.netology.nmedia.repository

import  androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post



class PostRepositoryInMemory : PostRepository {

    private var nextId = 0L
    private var posts = listOf(
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это второй пост и новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                    "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                    "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                    "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                    "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
            share = 0

        ), Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                    "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                    "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                    "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                    "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
            share = 0


        ), Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это очень новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                    "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                    "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                    "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                    "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
            share = 0
        ), Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это еще новее Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                    "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                    "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                    "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                    "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
            share = 0,
            video = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"

        ), Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это самая новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                    "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                    "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                    "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                    "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
            share = 0
        )
    )
        .reversed()

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
                )
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(share = post.share + 1)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {
            it.id != id
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = ++nextId,
                    published = "Now",
                    author = "Me",
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts

    }
}