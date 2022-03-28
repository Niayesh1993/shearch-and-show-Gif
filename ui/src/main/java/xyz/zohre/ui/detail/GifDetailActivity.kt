package xyz.zohre.ui.detail

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_gif_detail.*
import kotlinx.android.synthetic.main.activity_gif_detail.age_badge
import kotlinx.android.synthetic.main.activity_gif_detail.gifImageView
import kotlinx.android.synthetic.main.activity_gif_detail.gifLink
import kotlinx.android.synthetic.main.activity_gif_detail.gifTitle
import xyz.zohre.data.model.GifData
import xyz.zohre.presentation.bindImage
import xyz.zohre.ui.R
import xyz.zohre.ui.search.SearchGifFragment

@AndroidEntryPoint
class GifDetailActivity : AppCompatActivity() {

    private lateinit var gifData: GifData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_detail)

        gifData = intent.getParcelableExtra(SearchGifFragment.EXTRAS_GIF_DATA)!!

        initView()

        backBtn.setOnClickListener { this.onBackPressed() }
    }

    private fun initView() {

        gifTitle.text = gifData.title
        gifLink.text = gifData.images.fixed_height_downSampled.url
        age_badge.text = gifData.rating
        bindImage(
            imageUrl = gifData.images.fixed_height_downSampled.url,
            imageView = gifImageView,
            listener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }


            }
        )
    }
}