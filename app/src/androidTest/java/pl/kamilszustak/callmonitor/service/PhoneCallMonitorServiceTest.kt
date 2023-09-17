package pl.kamilszustak.callmonitor.service

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ServiceTestRule
import org.junit.Rule
import org.junit.Test

class PhoneCallMonitorServiceTest {

    // region Rules

    @get:Rule
    val serviceRule: ServiceTestRule = ServiceTestRule()

    // endregion

    // region Tests

    @Test
    fun test() {
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            PhoneCallMonitorService::class.java
        )
        serviceRule.startService(intent)

        val binder = serviceRule.bindService(intent)
    }

    // endregion

}