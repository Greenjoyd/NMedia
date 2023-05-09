package ru.netology.nmedia

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.createChooser
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.Postlistener
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)



        val newPostContract = registerForActivityResult(NewPostActivity.NewPostContract) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        val editPostContract =
            registerForActivityResult(NewPostActivity.EditPostContract) { result ->
                result ?: return@registerForActivityResult
                viewModel.changeContent(result)
                viewModel.save()
            }


        val adapter = PostAdapter(object : Postlistener {

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                editPostContract.launch(post.content)

            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onVideo(post: Post) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(post.video)
                )
                startActivity(intent)
            }


            override fun share(post: Post) {
                viewModel.share(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val startIntent = createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(startIntent)
            }
        }
        )
        activityMainBinding.add.setOnClickListener {
            newPostContract.launch()
            viewModel.cancelEdit()
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }


        activityMainBinding.list.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        viewModel.cancelEdit()
    }

}



