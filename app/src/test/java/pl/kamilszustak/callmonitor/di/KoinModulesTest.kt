package pl.kamilszustak.callmonitor.di

import android.content.Context
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class KoinModulesTest {

    // region Tests

    @Test
    fun `Koin dependency graph should be created without any issue`() {
        // given
        val module = module {
            includes(allModules)
        }

        // when
        module.verify(
            extraTypes = listOf(Context::class)
        )
    }

    // endregion

}