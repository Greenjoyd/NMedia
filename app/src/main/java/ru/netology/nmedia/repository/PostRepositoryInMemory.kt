package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory: PostRepository {
  private  var post = listOf(Post(
        id = 2,
        author ="Нетология. Университет интернет-профессий будущего",
        published = "21 мая в 18:36",
        content = "Привет, это второй пост и новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
      share = 0

    ) ,Post(
          id = 1,
      author ="Нетология. Университет интернет-профессий будущего",
      published = "21 мая в 18:36",
      content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
              "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
              "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
              "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
              "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb",
      share = 0

  )
  )

    private val data = MutableLiveData(post)

    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        post = post.map{ post ->
        if(post.id == id){
            post.copy(likedByMe = !post.likedByMe)
        }else{
             post
        }
        }
        data.value = post
    }

    override fun shareById(id:Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(share = +1)
            } else {
                post
            }
        }
        data.value = post
    }
    }
