package pl.kamilszustak.callmonitor.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assert
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.Test

class SerializationTest {

    // region Tests

    @Test
    fun `classes annotated with 'Serializable' have all properties annotated with 'SerialName'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(Serializable::class)
            .properties()
            .assert { property ->
                property.hasAnnotationOf(SerialName::class)
            }
    }

    // endregion

}