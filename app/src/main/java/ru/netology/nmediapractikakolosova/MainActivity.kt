package ru.netology.nmediapractikakolosova

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SoundEffectConstants
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageButton
import org.jetbrains.annotations.Nullable
import ru.netology.nmediapractikakolosova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 999999,
            share = 999,
            likedByMe = false
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            osnovnoitext.text = post.content
            textlike.text = post.likes.toString()
            textShare.text = post.share.toString()
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.heart_icon_icons_com_50374)
            }
            share?.setOnClickListener {
                post.share++
                textShare.text = post.share.toString()
                when {
                    post.share<1000 ->textShare.text =post.share.toString()
                    post.share in 1000..999999 ->textShare.text ="${post.share/1000}K"
                    else->textShare.text =String.format("%.1fM",post.share.toDouble()/1000000)
                }

            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.heart_icon_icons_com_50374
                    else R.drawable.heart_66744
                )
                if (post.likedByMe) post.likes++ else post.likes--
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 ->textlike.text ="${post.likes/1000}K"
                    post.likes<1000->textlike.text =post.likes.toString()
                    else->textlike.text =String.format("%.1fM",post.likes.toDouble()/1000000)
                }
            }
        }
    }

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var share: Int,
        var likedByMe: Boolean = false
    )
}





