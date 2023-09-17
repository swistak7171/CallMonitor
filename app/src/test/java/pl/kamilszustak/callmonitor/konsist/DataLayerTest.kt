package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParent
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class DataLayerTest {

    // region Tests

    @Test
    fun `classes implementing repository interfaces should have 'Impl' suffix in their names`() {
        Konsist.scopeFromProject()
            .classes()
            .withParent { it.name.endsWith("Repository") }
            .assert { it.hasNameEndingWith("Impl") }
    }

    @Test
    fun `every repository class has test class`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("RepositoryImpl")
            .assert { it.hasTestClass() }
    }

    @Test
    fun `interfaces with 'Repository' suffix should reside in 'domain' and 'repository' packages`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withNameEndingWith("Repository")
            .assert { it.resideInPackage("..domain..repository..") }
    }

    @Test
    fun `classes with 'RepositoryImpl' suffix should reside in 'data' and 'repository' packages`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("RepositoryImpl")
            .assert { it.resideInPackage("..data..repository..") }
    }

    // endregion

}