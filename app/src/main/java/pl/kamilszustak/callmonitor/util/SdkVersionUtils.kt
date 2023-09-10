package pl.kamilszustak.callmonitor.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun isSdkVersionAtLeast(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}