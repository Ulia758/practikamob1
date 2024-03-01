package ru.netology.myapplication.activity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.myapplication.databinding.CardPostBinding
import ru.netology.myapplication.dto.Post
import ru.netology.myapplication.viewmodel.PostViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.netology.myapplication.R
import ru.netology.myapplication.activity.FeedFragment.Companion.postArg
import ru.netology.myapplication.repository.PostRepository

class PostViewHandlers(
    private val binding: CardPostBinding,
    private val context : Context,
    private val viewModel: PostViewModel
)
{
    private val likes = 0
    private val share = 0
    private val crudHelper  = 0
    fun bind(post : Post) {
        with(binding)
        {
            author.text = post.author
            published.text = post.published
            osnovnoitext.text = post.content
            if (post.video != null) {
                video.visibility = View.VISIBLE
                setPlayVideoHandler(post)
            } else {
                video.visibility = View.GONE
            }
            binding.cardPost.setOnClickListener {
                val navController = findNavController(this.cardPost)
                navController.navigate(R.id.action_feedFragment_to_cardFragment, Bundle().apply {
                    postArg = post
                })
            }
        }
    }
    private fun setPlayVideoHandler(post : Post){
        with(binding)
        {
            val onClick = fun(_: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video?.toString()))
                share.toString()
                context.startActivity(intent)
            }
            play.setOnClickListener(onClick)
            video.setOnClickListener(onClick)
        }
    }
}