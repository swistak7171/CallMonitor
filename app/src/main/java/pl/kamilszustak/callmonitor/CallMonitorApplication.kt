package pl.kamilszustak.callmonitor

import android.app.Application
import pl.kamilszustak.callmonitor.server.startPhoneCallMonitorServer

class CallMonitorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startPhoneCallMonitorServer()
    }

}