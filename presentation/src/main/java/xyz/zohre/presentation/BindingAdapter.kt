package xyz.zohre.presentation

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
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
    placeholder: Int? = null,
    circleCrop: Boolean? = false,
    crossFade: Boolean? = false,
    overrideWidth: Int? = null,
    overrideHeight: Int? = null,
    listener: RequestListener<Drawable>? = null
) {

    val withContext = Glide.with(imageView.context)
    val request = if (!imageUrl.isNullOrBlank()) {
        withContext
            .load("""$imageUrl""")
            .error(R.drawable.bg_no_image)
            .also {
                if (placeholder != null) {
                    it.placeholder(placeholder)
                }
                if (circleCrop == true) {
                    it.circleCrop()
                }
                if (crossFade == true) {
                    it.transition(DrawableTransitionOptions.withCrossFade())
                }
                if (overrideWidth != null && overrideHeight != null) {
                    it.override(overrideWidth, overrideHeight)
                }
                if (listener != null) {
                    it.listener(listener)
                }
            }
    } else {
        withContext.load(R.drawable.bg_no_image)
    }
    request.into(imageView)
}