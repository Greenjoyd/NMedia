package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.adapter.PostAdapter
import ru.netology.nmedia.utils.Show
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter {
            viewModel.likeById(it.id)
        }

        viewModel.data.observe(this) { posts ->
            adapter.data = posts
        }

        activityMainBinding.list.adapter = adapter

    }
}



