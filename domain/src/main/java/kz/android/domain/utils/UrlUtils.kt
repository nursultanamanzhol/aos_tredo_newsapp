package kz.android.domain.utils

import android.net.Uri

object UrlUtils {
    fun normalizeUrl(url: String): String {
        return Uri.decode(Uri.encode(url.trim()))
    }
}

