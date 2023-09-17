package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class PackageTest {

    // region Tests

    @Test
    fun `package name must match file path`() {
        Konsist.scopeFromProject()
            .packages
            .assert { it.hasMatchingPath }
    }

    // endregion

}