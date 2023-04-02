package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Show

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit,
) : ViewHolder(binding.root) {

    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.likered else R.drawable.ic_round_favorite_border_24
            )
            viewCount.text = Show.showCount(post.views)

            like.setOnClickListener {
                onLikeClicked(post)
            }
            likeCount.text = Show.showCount(post.likes)
            binding.shareBt.setOnClickListener {
                onShareClicked(post)
            }
            shareCount.text = Show.showCount(post.share)
        }
    }
}