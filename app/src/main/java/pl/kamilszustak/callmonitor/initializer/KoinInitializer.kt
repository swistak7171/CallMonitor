package pl.kamilszustak.callmonitor.initializer

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.kamilszustak.callmonitor.di.loggerModule
import pl.kamilszustak.callmonitor.di.phoneCallModule

class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidLogger(Level.DEBUG)
            androidContext(context)

            modules(
                loggerModule,
                phoneCallModule,
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}