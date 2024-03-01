package ru.netology.myapplication.viewmodel
import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.myapplication.dto.Post
import ru.netology.myapplication.repository.PostRepository
import ru.netology.myapplication.repository.PostRepositoryFileImpl
import ru.netology.myapplication.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    shareByMe = false,
    likes = 0,
    share = 0
)

class PostViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post:Post){
        edited.value = post
    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
    fun likeById(id: Int)=repository.likeById(id)
    fun shareById(id: Int)=repository.shareById(id)
    fun removeById(id: Int) = repository.removeById(id)

}