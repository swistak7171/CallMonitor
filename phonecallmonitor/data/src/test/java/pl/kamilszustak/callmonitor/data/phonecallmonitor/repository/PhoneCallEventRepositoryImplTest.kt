package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import app.cash.turbine.test
import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDomainModel
import kotlin.test.assertEquals

class PhoneCallEventRepositoryImplTest {

    // region Fields

    private val phoneCallEventDataSourceMock: PhoneCallEventDataSource = mockk()

    private val phoneCallEventRepository: PhoneCallEventRepositoryImpl =
        PhoneCallEventRepositoryImpl(
            phoneCallEventDataSource = phoneCallEventDataSourceMock
        )

    // endregion

    // region Tests

    @Test
    fun `'getRx()' should emit phone call events`() = runTest {
        mockkStatic(PhoneCallEventDataModel::toDomainModel) {
            // given
            val eventDataModels = listOf(
                phoneCallStartEventDataModel,
                phoneCallEndEventDataModel
            )
            val expectedResults = listOf(
                phoneCallStartEventDomainModel,
                phoneCallEndEventDomainModel
            )

            every {
                phoneCallStartEventDataModel.toDomainModel()
            } returns phoneCallStartEventDomainModel
            every {
                phoneCallEndEventDataModel.toDomainModel()
            } returns phoneCallEndEventDomainModel
            every { phoneCallEventDataSourceMock.getRx() } returns eventDataModels.asFlow()

            // when
            phoneCallEventRepository.getRx()
                .test {
                    // then
                    expectedResults.forEach { expectedResult ->
                        assertEquals(expectedResult, awaitItem())
                    }
                    awaitComplete()
                    ensureAllEventsConsumed()

                    verify(ordering = Ordering.SEQUENCE) {
                        phoneCallEventDataSourceMock.getRx()
                        phoneCallStartEventDataModel.toDomainModel()
                        phoneCallEndEventDataModel.toDomainModel()
                    }
                    confirmVerified(phoneCallEventDataSourceMock)
                }
        }
    }

    // endregion

}