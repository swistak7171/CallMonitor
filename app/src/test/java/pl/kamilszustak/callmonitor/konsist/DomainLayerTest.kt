package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParent
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class DomainLayerTest {

    // region Tests

    @Test
    fun `classes implementing use case interfaces should have 'Impl' suffix in their names`() {
        Konsist.scopeFromProject()
            .classes()
            .withParent { it.name.endsWith("UseCase") }
            .assert { it.hasNameEndingWith("Impl") }
    }

    @Test
    fun `classes with 'UseCase' suffix should have at most two public methods named 'execute' and 'executeRx'`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase", "UseCaseImpl")
            .assert { klass ->
                val publicFunctions = klass.functions()
                    .filter { it.hasPublicModifier }
                val haveMethodsWithAllowedNames = publicFunctions.all { function ->
                    function.name == "execute" || function.name == "executeRx"
                }
                val hasAtMostOneExecuteFunction = publicFunctions.count { function ->
                    function.name == "execute"
                } <= 1
                val hasAtMostOneExecuteRxFunction = publicFunctions.count { function ->
                    function.name == "executeRx"
                } <= 1

                haveMethodsWithAllowedNames && hasAtMostOneExecuteFunction && hasAtMostOneExecuteRxFunction
            }
    }

    @Test
    fun `every use case class has test class`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("UseCaseImpl")
            .assert { it.hasTestClass() }
    }

    @Test
    fun `classes with 'UseCaseImpl' suffix should reside in 'domain' and 'usecase' packages`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCaseImpl")
            .assert { it.resideInPackage("..domain..usecase..") }
    }

    @Test
    fun `classes with 'UseCase' suffix should reside in 'domain' and 'usecase' packages`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withNameEndingWith("UseCase")
            .assert { it.resideInPackage("..domain..usecase..") }
    }

    // endregion

}