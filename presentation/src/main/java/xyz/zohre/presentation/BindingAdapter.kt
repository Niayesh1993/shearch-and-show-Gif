package xyz.zohre.presentation

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

@BindingAdapter(
    "imageUrl",
    "imagePlaceholder",
    "circleCropImage",
    "crossFadeImage",
    "overrideImageWidth",
    "overrideImageHeight",
    "imageLoadListener",
    requireAll = false
)
fun bindImage(
    imageView: ImageView,
    imageUrl: String?,
    listener: RequestListener<Drawable>? = null
) {

    val withContext = Glide.with(imageView.context)
    val request = if (!imageUrl.isNullOrBlank()) {
        withContext
            .load("""$imageUrl""")
            .error(R.drawable.bg_no_image)
    } else {
        withContext.load(R.drawable.bg_no_image)
    }
    request.into(imageView)
}