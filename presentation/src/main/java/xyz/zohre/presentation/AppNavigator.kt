package xyz.zohre.presentation

interface AppNavigator {
    fun navigateTo(appPage: AppPage)
}

sealed class AppPage {
    object HomePage : AppPage()
    object SearchPage : AppPage()
}