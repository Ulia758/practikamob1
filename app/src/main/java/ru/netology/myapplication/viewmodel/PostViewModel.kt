package ru.netology.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.myapplication.repository.PostRepository
import ru.netology.myapplication.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Int)=repository.likeById(id)
    fun shareById(id: Int)=repository.shareById(id)
}