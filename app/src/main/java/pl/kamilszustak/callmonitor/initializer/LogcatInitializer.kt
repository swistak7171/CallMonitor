package pl.kamilszustak.callmonitor.initializer

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class LogcatInitializer : Initializer<Unit> {

    // region Initializer Implementation

    override fun create(context: Context) {
        AndroidLogcatLogger.installOnDebuggableApp(
            application = context as Application,
            minPriority = LogPriority.VERBOSE,
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    // endregion

}