package xyz.zohre.presentation

interface AppNavigator {
    fun navigateTo(appPage: AppPage, quest: String? = "")
}

sealed class AppPage {
    object RandomPage : AppPage()
    object SearchPage : AppPage()
}