package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Show

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val post = Post(
            id = 0,
            author = getString(R.string.netology),
            published = getString(R.string.published),
            content = getString(R.string.content)
        )


        with(binding) {
            author.text = post.author
            content.text = post.content

            if (post.likedByMe) {
                like.setImageResource(R.drawable.likered)
            }

            viewCount.text = Show.showCount(post.views)

            likeCount.text = Show.showCount(post.likes)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes = if (post.likedByMe) {
                    post.likes + 1
                } else {
                    post.likes - 1
                }
                likeCount.text = Show.showCount(post.likes)

                like.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.likered
                    } else R.drawable.ic_round_favorite_border_24
                )
            }


            shareCount.text = Show.showCount(post.share)

            shareBt.setOnClickListener {
                post.share++
                println(post.share)
                shareCount.text = Show.showCount(post.share)
            }
        }
    }
}