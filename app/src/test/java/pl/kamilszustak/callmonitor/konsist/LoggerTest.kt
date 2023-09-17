package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.Test

class LoggerTest {

    // region Tests

    @Test
    fun `no class should use Android util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertNot { file ->
                file.hasImport { import ->
                    import.name == "android.util.Log"
                }
            }
    }

    // endregion

}