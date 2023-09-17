package pl.kamilszustak.callmonitor.konsist

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentOf
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class PresentationLayerTest {

    // region Tests

    @Test
    fun `classes extending 'ViewModel' or 'AndroidViewModel' should have 'ViewModel' suffix in their names`() {
        Konsist.scopeFromProject()
            .classes()
            .withParentOf(ViewModel::class, AndroidViewModel::class)
            .assert { it.hasNameEndingWith("ViewModel") }
    }

    @Test
    fun `every ViewModel class has test class`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("ViewModel")
            .assert { it.hasTestClass() }
    }

    @Test
    fun `classes with 'ViewModel' suffix should reside in 'presentation' and 'viewmodel' packages`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .assert { it.resideInPackage("..presentation..viewmodel..") }
    }

    // endregion

}