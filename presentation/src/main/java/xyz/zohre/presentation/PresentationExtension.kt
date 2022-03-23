package xyz.zohre.presentation

import android.view.View
import com.google.android.material.snackbar.Snackbar
import xyz.zohre.domain.exeption.RemoteCallException

fun Throwable.parseErrorStringRes(): TextData {
    return when (this) {
        is RemoteCallException -> {
            TextData.TextStringRes(R.string.remote_error)
        }
        else -> {
            TextData.TextStringRes(R.string.unknown_error)
        }
    }
}

fun TextData.shortToast(view: View) {
    when (this) {
        is TextData.TextString -> Snackbar.make(view, this.text, Snackbar.LENGTH_LONG).show()
        is TextData.TextStringRes -> Snackbar.make(view, this.resId, Snackbar.LENGTH_LONG).show()
    }


}