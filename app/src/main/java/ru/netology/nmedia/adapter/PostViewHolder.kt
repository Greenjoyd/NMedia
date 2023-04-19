package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Show


class PostViewHolder(
    private val binding: CardPostBinding,
    private val listiner: Postlistener,
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
                listiner.onLike(post)
            }
            likeCount.text = Show.showCount(post.likes)
            binding.shareBt.setOnClickListener {
                listiner.share(post)
            }
            shareCount.text = Show.showCount(post.share)

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listiner.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listiner.onEdit(post)
                                true
                            }
                            else -> false
                        }

                    }
                }
                    .show()
            }
        }
    }
}