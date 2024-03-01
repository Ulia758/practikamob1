package ru.netology.myapplication.data

import ru.netology.myapplication.dto.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Int)
    fun removeById(id: Int)
    fun shareById(id: Int)
}