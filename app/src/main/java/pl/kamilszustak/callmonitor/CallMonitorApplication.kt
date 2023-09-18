package pl.kamilszustak.callmonitor

import android.app.Application
import org.koin.core.component.KoinComponent
import pl.kamilszustak.callmonitor.server.startPhoneCallMonitorServer

/**
 * An [Application] class responsible for starting the phone call monitor server.
 */
class CallMonitorApplication : Application(), KoinComponent {

    // region Application Implementation

    override fun onCreate() {
        super.onCreate()

        startPhoneCallMonitorServer()
    }

    // endregion

}