package ru.netology.myapplication.activity

import android.content.Intent
import android.net.Uri
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
            override fun onVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/live/kqwMNnFetss?si=OasLn0lLLSl8z26a"))
                startActivity(intent)
            }
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
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        viewModel.edited.observe(this){ post->
            if(post.id == 0){
                return@observe
            }
            newPostLauncher.launch(post.content)
        }
        binding.cancel.setOnClickListener {
            newPostLauncher.launch(null)
//            with(binding.content){
//                viewModel.save()
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//                binding.group.visibility = View.GONE
//            }
        }
        binding.fab.setOnClickListener {
            newPostLauncher.launch(null)
//            with(binding.content){
//                if(text.isNullOrBlank()){
//                    Toast.makeText(
//                        this@MainActivity,
//                        context.getString(R.string.error_empty_content),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//                viewModel.changeContent(text.toString())
//                viewModel.save()
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch(null)
        }

    }

}






