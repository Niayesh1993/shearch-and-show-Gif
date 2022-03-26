package xyz.zohre.ui.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.gif_item.view.*
import xyz.zohre.data.model.GifData
import xyz.zohre.presentation.adapter.BaseRecyclerAdapter
import xyz.zohre.presentation.adapter.BaseViewHolder
import xyz.zohre.presentation.bindImage
import xyz.zohre.ui.R

class GifRecyclerAdapter: BaseRecyclerAdapter<GifData,
        GifRecyclerAdapter.ViewHolder,
        BaseViewHolder.OnItemClickListener<GifData>>()
{

    class ViewHolder(itemView: View,
                     adapter: GifRecyclerAdapter,)
        : BaseViewHolder<GifData>(itemView, adapter){
        override fun bind(t: GifData) {

            bindImage(
                imageUrl = t.images.fixed_width_small_still.url,
                imageView = itemView.gifImageView,
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
    override fun viewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.gif_item,
                parent,
                false
            ),
            this
        )    }

    override fun onBindView(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])    }
}