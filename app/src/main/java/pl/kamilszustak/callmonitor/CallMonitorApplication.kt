package pl.kamilszustak.callmonitor

import android.app.Application
import org.koin.core.component.KoinComponent
import pl.kamilszustak.callmonitor.server.startPhoneCallMonitorServer

class CallMonitorApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startPhoneCallMonitorServer()
    }

}