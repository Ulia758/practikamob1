package ru.netology.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.myapplication.data.PostDao
import ru.netology.myapplication.dto.Post

class PostRepositorySQLiteImpl (
    private val dao: PostDao
) : PostRepository {
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        posts = dao.getAll()
        data.value = posts
    }
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Int) {
        dao.likeById(id)
        posts = dao.getAll()
        data.value = posts
    }

    override fun shareById(id: Int) {
        dao.shareById(id)
        posts = dao.getAll()
        data.value = posts
    }

    override fun removeById(id: Int) {
        dao.removeById(id)
        posts = dao.getAll()
        data.value = posts
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (id == 0) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        data.value = posts
    }
}