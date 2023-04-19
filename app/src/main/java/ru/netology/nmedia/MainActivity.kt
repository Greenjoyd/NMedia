package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.Postlistener
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(object : Postlistener {

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun share(post: Post) {
                viewModel.share(post.id)
            }
        }
        )

        viewModel.edited.observe(this){
            if (it.id == 0L){
                return@observe
            }

            activityMainBinding.content.requestFocus()
            activityMainBinding.content.setText(it.content)
        }

        activityMainBinding.save.setOnClickListener {
            with(activityMainBinding.content) {
                val content = text.toString()
                if (content.isBlank()) {
                    Toast.makeText(
                        this@MainActivity, R.string.empty_post_error, Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                activityMainBinding.cancel.setOnClickListener {
                    with(activityMainBinding.content){
                        setText("")
                    }
                }


                viewModel.changeContent(content)
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }


        activityMainBinding.list.adapter = adapter


    }
}



