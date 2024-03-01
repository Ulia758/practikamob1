package ru.netology.myapplication.dto

import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize
import java.net.URL
@Parcelize
data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    var share: Int,
    val likedByMe: Boolean,
    val shareByMe: Boolean,
    val video: URL?=null
): Parcelable
