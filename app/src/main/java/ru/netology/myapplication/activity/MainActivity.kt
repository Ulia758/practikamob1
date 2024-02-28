package ru.netology.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.myapplication.adapter.OnInteractionListener
import ru.netology.myapplication.adapter.PostsAdapter
import ru.netology.myapplication.databinding.ActivityMainBinding
import ru.netology.myapplication.util.AndroidUtils
import ru.netology.myapplication.viewmodel.PostViewModel
import ru.netology.myapplication.R
import ru.netology.myapplication.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }
            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }
        })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this){ post->
            if(post.id == 0){
                return@observe
            }
            with(binding.content){
                binding.group.visibility = View.VISIBLE
                requestFocus()
                setText(post.content)
            }
        }
        binding.cancel.setOnClickListener {
            with(binding.content){
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.group.visibility = View.GONE
            }
        }
        binding.save.setOnClickListener {
            with(binding.content){
                if(text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

    }

}






