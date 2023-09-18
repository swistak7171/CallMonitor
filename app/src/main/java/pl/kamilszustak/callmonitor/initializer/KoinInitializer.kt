package pl.kamilszustak.callmonitor.initializer

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import pl.kamilszustak.callmonitor.di.allModules

/**
 * An [Initializer] of the [Koin] dependency injection framework. It is used to initialize
 * the framework at the application startup.
 */
class KoinInitializer : Initializer<KoinApplication> {

    // region Initializer Implementation

    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(allModules)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    // endregion

}