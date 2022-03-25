package xyz.zohre.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.progressbar
import xyz.zohre.presentation.BaseFragment
import xyz.zohre.presentation.shortToast
import xyz.zohre.ui.R


private const val SEARCH_QUERY = "param1"

@AndroidEntryPoint
class SearchGifFragment : BaseFragment() {

    private var query: String? = null
    private val viewModel: SearchGifViewModel by getLazyViewModel()
    private val adapter = GifRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(SEARCH_QUERY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gif_recycler.adapter = adapter
        gif_recycler.itemAnimator = DefaultItemAnimator()
        val gridLayoutManager = GridLayoutManager(activity?.applicationContext, 3)
        gif_recycler.layoutManager = gridLayoutManager
        initObservers()
        query?.let { viewModel.searchGif(it) }
        progressbar.visibility = View.VISIBLE

    }

    private fun initObservers() {
        viewModel.gifs.observe(
            viewLifecycleOwner,
            {
                progressbar.visibility = View.GONE
                adapter.insertItems(it)
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


    companion object {

        @JvmStatic
        fun newInstance(param: String?) =
            SearchGifFragment().apply {
                arguments = Bundle().apply {
                   this.putString(SEARCH_QUERY, param)
                }
            }
    }
}