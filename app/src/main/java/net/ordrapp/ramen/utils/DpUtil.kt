package net.ordrapp.ramen.utils

import android.content.res.Resources
import android.util.DisplayMetrics


fun dp(dp: Int): Int {
    val metrics = Resources.getSystem().displayMetrics
    return (dp * metrics.density).toInt()
}