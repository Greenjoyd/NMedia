package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory: PostRepository {
  private  var post = Post(
        id = 1,
        author ="Нетология. Университет интернет-профессий будущего",
        published = "21 мая в 18:36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов\n" +
                "    по онлайн маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы\n" +
                "     растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное\n" +
                "    остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться\n" +
                "    выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку пермен → http://netolo.gy/fyb"

    )

    private val data = MutableLiveData(post)

    override fun getData(): LiveData<Post> =data

    override fun like() {
        post = post.copy(
            likes = if(post.likedByMe)post.likes-1 else post.likes+1,
            likedByMe = !post.likedByMe,
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(
            share = post.share+1
        )
        data.value = post
    }

}