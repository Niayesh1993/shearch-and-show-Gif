package xyz.zohre.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appNavigator: AppNavigator

    inline fun <reified T : ViewModel> getLazyViewModel(): Lazy<T> =
        lazy { ViewModelProvider(this, viewModelFactory).get(T::class.java) }
}