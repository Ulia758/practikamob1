package ru.netology.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.myapplication.R
import android.content.Intent
import androidx.navigation.fragment.findNavController
import ru.netology.myapplication.databinding.FragmentCardBinding
import ru.netology.myapplication.dto.Post
import ru.netology.myapplication.util.PostArg
import ru.netology.myapplication.viewmodel.PostViewModel
import ru.netology.myapplication.activity.NewPostFragment.Companion.textArg


class CardFragment : Fragment() {
    companion object{
        var Bundle.postArg : Post? by PostArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCardBinding.inflate(inflater,
            container,
            false)

        context?.let {
            val handlers = PostViewHandlers(binding.cardPost, it, viewModel)
            val post = arguments?.postArg
            post?.let {
                handlers.bind(post)
                binding.cardPost.cardPost.setOnClickListener(null)
            }
            viewModel.data.observe(viewLifecycleOwner) { posts ->
                post?.let {
                    val newPost = posts.find { newPost -> newPost.id == post.id }
                    if (newPost != null) {
                        handlers.bind(newPost)
                        binding.cardPost.cardPost.setOnClickListener(null)
                    } else {
                        findNavController().popBackStack()
                    }
                }
            }
        }
        return binding.root
    }
}