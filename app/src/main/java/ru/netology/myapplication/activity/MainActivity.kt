package ru.netology.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.myapplication.databinding.ActivityMainBinding
import androidx.activity.viewModels
import ru.netology.myapplication.adapter.PostsAdapter
import ru.netology.myapplication.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter({
            viewModel.likeById(it.id)},
            {
                viewModel.shareById(it.id)
            })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}






