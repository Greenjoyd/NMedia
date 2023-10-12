package ru.netology.nmedia.adapter

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post


class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: Postlistener,
) : ViewHolder(binding.root) {

    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            if (post.video.isBlank()){
                videoBt.visibility = View.GONE;playBt.visibility = View.GONE
            } else{videoBt.visibility = View.VISIBLE; playBt.visibility = View.VISIBLE}

            videoBt.setOnClickListener{
                listener.onVideo(post)
            }
            playBt.setOnClickListener{
                listener.onVideo(post)
            }

            like.isChecked = post.likedByMe
            like.text = post.likes.toString()
            viewsBt.text = post.views.toString()
            shareBt.text = post.share.toString()



            like.setOnClickListener {
                listener.onLike(post)
            }
            binding.shareBt.setOnClickListener {
                listener.share(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
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