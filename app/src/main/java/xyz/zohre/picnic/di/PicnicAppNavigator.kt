package xyz.zohre.picnic.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.minimoneybox.R
import dagger.hilt.android.qualifiers.ActivityContext
import xyz.zohre.presentation.AppNavigator
import xyz.zohre.presentation.AppPage
import xyz.zohre.ui.random.RandomGifFragment
import xyz.zohre.ui.search.SearchGifFragment
import javax.inject.Inject

class PicnicAppNavigator@Inject constructor(@ActivityContext private val activity: Context):
    AppNavigator {
    override fun navigateTo(appPage: AppPage) {
        when (appPage) {
            is AppPage.HomePage -> {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RandomGifFragment.newInstance()).
                    commit()
            }
            is AppPage.SearchPage -> {
                (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchGifFragment.newInstance()).
                    commit()
            }
        }
    }
}