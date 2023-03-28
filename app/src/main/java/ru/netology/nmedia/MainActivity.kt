package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.Show
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        fun roundOffDecimal(number: Double): Double? {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.FLOOR
            return df.format(number).toDouble()
        }

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

            viewCount.text = Show().showViews(post)

            likeCount.text = Show().showLike(post)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes = if (post.likedByMe) {
                    post.likes + 1
                } else {
                    post.likes - 1
                }
                likeCount.text = post.likes.toString()

                like.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.likered
                    } else R.drawable.ic_round_favorite_border_24
                )
            }


            shareCount.text = Show().showShare(post)

            shareBt.setOnClickListener {
                post.share++
                println(post.share)
                if (post.share < 1000) {
                    shareCount.text = post.share.toString()
                } else if (post.share >= 1000 && post.share < 10000) {
                    var decimalShare = roundOffDecimal(post.share.toDouble() / 1000)
                    shareCount.text = decimalShare.toString() + "K"
                } else if (post.share >= 10000 && post.share < 100000) {
                    var st = post.share.toString()
                    var ch = st.substring(0, 2)
                    shareCount.text = ch + "K"
                } else if (post.share >= 100000 && post.share < 1000000) {
                    var st = post.share.toString()
                    var ch = st.substring(0, 3)
                    shareCount.text = ch + "K"
                } else if (post.share >= 1000000 && post.share < 10000000) {
                    var decimalShare = roundOffDecimal(post.share.toDouble() / 1000000)
                    shareCount.text = decimalShare.toString() + "M"
                } else {
                    shareCount.text = "10M+"
                }
            }
        }
    }
}