package ru.netology.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.myapplication.repository.PostRepository
import ru.netology.myapplication.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like()=repository.like()
    fun share()=repository.share()
}