package xyz.zohre.ui.random

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random.*
import xyz.zohre.presentation.BaseFragment
import xyz.zohre.presentation.bindImage
import xyz.zohre.presentation.shortToast
import xyz.zohre.ui.R

@AndroidEntryPoint
class RandomGifFragment : BaseFragment() {

    private val viewModel: RandomGifViewModel by getLazyViewModel()
    private val updateHandler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        viewModel.searchRandomGif()
        progressbar.visibility = View.VISIBLE
    }

    private fun initHandler() {
        updateHandler.postDelayed(Runnable {
            updateHandler.postDelayed(runnable!!, DELAY.toLong())
            viewModel.searchRandomGif()
        }.also { runnable = it }, DELAY.toLong())
    }

    private fun initObservers() {
        viewModel.randomGif.observe(
            viewLifecycleOwner,
            {
                progressbar.visibility = View.GONE
                gifTitle.text = it.gifData.title
                gifLink.text = it.gifData.images.fixed_height_downsampled.url
                age_badge.tag = it.gifData.rating
                bindImage(
                    imageUrl = it.gifData.images.fixed_height_downsampled.url,
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
        )
        viewModel.loading.observe(
            viewLifecycleOwner,
            {
                if (it) progressbar.visibility = View.VISIBLE
            }
        )
        viewModel.showError.observe(
            viewLifecycleOwner,
            { event ->
                event.getContentIfNotHandled()?.shortToast(this.requireView())
                progressbar.visibility = View.GONE
            }
        )
    }

    override fun onResume() {
        super.onResume()
        initHandler()

    }

    override fun onPause() {
        super.onPause()
        updateHandler.removeCallbacks(runnable!!)
    }
    companion object {

        private const val DELAY = 10000
        @JvmStatic
        fun newInstance() =
            RandomGifFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}