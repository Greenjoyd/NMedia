package ru.netology.nmedia.adapter

import android.view.View
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

            if (post.video){videoBt.visibility = View.VISIBLE;playBt.visibility = View.VISIBLE}

            videoBt.setOnClickListener{
                listiner.onVideo(post)
            }
            playBt.setOnClickListener{
                listiner.onVideo(post)
            }

            like.isChecked = post.likedByMe
            like.text = post.likes.toString()
            viewsBt.text = post.views.toString()
            shareBt.text = post.share.toString()



            like.setOnClickListener {
                listiner.onLike(post)
            }
            binding.shareBt.setOnClickListener {
                listiner.share(post)
            }

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