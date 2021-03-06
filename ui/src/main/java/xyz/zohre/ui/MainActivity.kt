package xyz.zohre.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import xyz.zohre.presentation.AppNavigator
import xyz.zohre.presentation.AppPage
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView.setOnQueryTextListener(this)

        backBtn.setOnClickListener {
            appNavigator.navigateTo(AppPage.RandomPage)
            searchView.clearFocus()
            backBtn.visibility = View.GONE
        }

        appNavigator.navigateTo(AppPage.RandomPage)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        backBtn.visibility = View.VISIBLE
        appNavigator.navigateTo(AppPage.SearchPage, query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        backBtn.visibility = View.VISIBLE
        appNavigator.navigateTo(AppPage.SearchPage, newText)
        return false
    }
}