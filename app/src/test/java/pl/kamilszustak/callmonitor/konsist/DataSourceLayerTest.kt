package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParent
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class DataSourceLayerTest {

    // region Tests

    @Test
    fun `classes implementing data source interfaces should have 'Impl' suffix in their names`() {
        Konsist.scopeFromProject()
            .classes()
            .withParent { it.name.endsWith("DataSource") }
            .assert { it.hasNameEndingWith("Impl") }
    }

    @Test
    fun `every data source class has test class`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("DataSourceImpl")
            .assert { it.hasTestClass() }
    }

    @Test
    fun `interfaces with 'DataSource' suffix should reside in 'data' and 'datasource' packages`() {
        Konsist.scopeFromProject()
            .interfaces()
            .withNameEndingWith("DataSource")
            .assert { it.resideInPackage("..data..datasource..") }
    }

    @Test
    fun `classes with 'DataSourceImpl' suffix should reside in 'datasource' and 'datasource' packages`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("DataSourceImpl")
            .assert { it.resideInPackage("..datasource..datasource..") }
    }

    // endregion

}