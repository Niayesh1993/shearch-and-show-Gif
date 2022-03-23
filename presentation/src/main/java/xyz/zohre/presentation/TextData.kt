package xyz.zohre.presentation

sealed class TextData {
    class TextStringRes(val resId: Int) : TextData()
    class TextString(val text: String) : TextData()
}
