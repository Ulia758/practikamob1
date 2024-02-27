package ru.netology.myapplication.repository
import androidx.lifecycle.LiveData
import ru.netology.myapplication.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
    fun shareById(id: Int)

}