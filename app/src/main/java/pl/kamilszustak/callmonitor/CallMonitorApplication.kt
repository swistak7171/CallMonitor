package pl.kamilszustak.callmonitor

import android.app.Application

class CallMonitorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startPhoneCallMonitorServer()
    }

}