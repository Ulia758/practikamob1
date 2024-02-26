package ru.netology.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.myapplication.databinding.ActivityMainBinding

import androidx.activity.viewModels
import ru.netology.myapplication.R


import ru.netology.myapplication.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                osnovnoitext.text = post.content
                textlike.text = post.likes.toString()
                textShare.text = post.share.toString()
                like.setImageResource(
                    if (post.likedByMe) R.drawable.heart_icon_icons_com_50374 else R.drawable.heart_66744
                )
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 -> textlike.text = "${post.likes / 1000}K"
                    post.likes < 1000 -> textlike.text = post.likes.toString()
                    else -> textlike.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
                }
                textShare.text = post.share.toString()
                when {
                    post.share < 1000 -> textShare.text = post.share.toString()
                    post.share in 1000..999999 -> textShare.text = "${post.share / 1000}K"
                    else -> textShare.text = String.format(
                        "%.1fM", post.share.toDouble() / 1000000
                    )
                }
            }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
            }
        }
    }
}






