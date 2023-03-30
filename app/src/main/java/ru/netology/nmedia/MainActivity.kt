package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.utils.Show
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {
                author.text = post.author
                content.text = post.content
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.likered)
                }else like.setImageResource(R.drawable.ic_round_favorite_border_24)
                viewCount.text = Show.showCount(post.views)
                likeCount.text = Show.showCount(post.likes)

                shareCount.text = Show.showCount(post.share)

            }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.shareBt.setOnClickListener {
             viewModel.share()
            }
        }
    }
}

