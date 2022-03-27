package xyz.zohre.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.progressbar
import xyz.zohre.data.model.GifData
import xyz.zohre.presentation.BaseFragment
import xyz.zohre.presentation.adapter.BaseViewHolder
import xyz.zohre.presentation.shortToast
import xyz.zohre.ui.R
import xyz.zohre.ui.detail.GifDetailActivity


@AndroidEntryPoint
class SearchGifFragment : BaseFragment() {

    private var query: String? = null
    private val viewModel: SearchGifViewModel by getLazyViewModel()
    private val adapter = GifRecyclerAdapter()
    private var offset = 0

    private var gifClickListener: BaseViewHolder.OnItemClickListener<GifData> =
        BaseViewHolder.OnItemClickListener { _, _gifData ->

            val intent = Intent(activity, GifDetailActivity::class.java)
            intent.putExtra(EXTRAS_GIF_DATA, _gifData)
            startActivity(intent)
        }

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
        val gridLayoutManager = GridLayoutManager(activity?.applicationContext, COLUMN)
        gif_recycler.layoutManager = gridLayoutManager
        adapter.listener = gifClickListener
        initObservers()
        query?.let { viewModel.searchGif(it, offset) }
        progressbar.visibility = View.VISIBLE

        gif_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    offset =+ 50
                    query?.let { viewModel.searchGif(it, offset) }
                }
            }
        })

    }

    private fun initObservers() {
        viewModel.gifs.observe(
            viewLifecycleOwner,
            {
                progressbar.visibility = View.GONE
                if (offset == 0) adapter.insertItems(it)
                else adapter.addItems(it)
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

        private const val SEARCH_QUERY = "search_query"
        const val EXTRAS_GIF_DATA = "gif_data"
        private const val COLUMN = 3

        @JvmStatic
        fun newInstance(param: String?) =
            SearchGifFragment().apply {
                arguments = Bundle().apply {
                   this.putString(SEARCH_QUERY, param)
                }
            }
    }
}