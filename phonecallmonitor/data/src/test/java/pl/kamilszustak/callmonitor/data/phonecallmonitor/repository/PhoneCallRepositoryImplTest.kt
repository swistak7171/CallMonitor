package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import app.cash.turbine.test
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.contactName
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.id
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toOngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.ongoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.ongoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDomainModelWithUnknownPhoneNumber
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneNumber
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.logger.Logger
import kotlin.test.assertEquals

class PhoneCallRepositoryImplTest {

    // region Fields

    private val loggerMock: Logger = mockk()
    private val ongoingPhoneCallDataSourceMock: OngoingPhoneCallDataSource = mockk()
    private val phoneCallLogDataSourceMock: PhoneCallLogDataSource = mockk()
    private val contactNameDataSourceMock: ContactNameDataSource = mockk()
    private val phoneCallMetadataDataSourceMock: PhoneCallMetadataDataSource = mockk()

    private val phoneCallRepository: PhoneCallRepository = PhoneCallRepositoryImpl(
        logger = loggerMock,
        ongoingPhoneCallDataSource = ongoingPhoneCallDataSourceMock,
        phoneCallLogDataSource = phoneCallLogDataSourceMock,
        contactNameDataSource = contactNameDataSourceMock,
        phoneCallMetadataDataSource = phoneCallMetadataDataSourceMock,
    )

    // endregion

    // region Tests

    @Test
    fun `'setStarted()' should change phone call status to started`() = runTest {
        mockkStatic(PhoneCallEventDomainModel.PhoneCallStart::toOngoingPhoneCallDataModel) {
            // given
            every {
                phoneCallStartEventDomainModel.toOngoingPhoneCallDataModel()
            } returns ongoingPhoneCallDataModel
            justRun { ongoingPhoneCallDataSourceMock.setStarted(ongoingPhoneCallDataModel) }

            // when
            phoneCallRepository.setStarted(phoneCallStartEventDomainModel)

            // then
            verify(ordering = Ordering.SEQUENCE) {
                phoneCallStartEventDomainModel.toOngoingPhoneCallDataModel()
                ongoingPhoneCallDataSourceMock.setStarted(ongoingPhoneCallDataModel)
            }
            confirmAllMocksVerified()
        }
    }

    @Test
    fun `'setEnded()' should change phone call status to ended and save it in phone call log`() =
        runTest {
            // given
            every { ongoingPhoneCallDataSourceMock.get() } returns ongoingPhoneCallDataModel
            justRun { ongoingPhoneCallDataSourceMock.setEnded() }
            justRun { phoneCallLogDataSourceMock.add(phoneCallLogEntryDataModel) }

            // when
            phoneCallRepository.setEnded(phoneCallEndEventDomainModel)

            // then
            verify(ordering = Ordering.SEQUENCE) {
                ongoingPhoneCallDataSourceMock.get()
                ongoingPhoneCallDataSourceMock.setEnded()
                phoneCallLogDataSourceMock.add(phoneCallLogEntryDataModel)
            }
            confirmAllMocksVerified()
        }

    @Test
    fun `'setEnded()' should not save phone call in log when there is no ongoing phone call currently`() =
        runTest {
            // given
            every { ongoingPhoneCallDataSourceMock.get() } returns null
            justRun { loggerMock.warn(any(), any()) }

            // when
            phoneCallRepository.setEnded(phoneCallEndEventDomainModel)

            // then
            verify(ordering = Ordering.SEQUENCE) {
                ongoingPhoneCallDataSourceMock.get()
                loggerMock.warn(any(), any())
            }
            confirmAllMocksVerified()
        }

    @Test
    fun `'setEnded()' should not save phone call in log when the ended phone call has a different phone number than the ongoing phone call`() =
        runTest {
            // given
            every { ongoingPhoneCallDataSourceMock.get() } returns ongoingPhoneCallDataModel
            justRun { loggerMock.warn(any(), any()) }

            // when
            phoneCallRepository.setEnded(phoneCallEndEventDomainModelWithUnknownPhoneNumber)

            // then
            verify(ordering = Ordering.SEQUENCE) {
                ongoingPhoneCallDataSourceMock.get()
                loggerMock.warn(any(), any())
            }
            confirmAllMocksVerified()
        }

    @Test
    fun `'getOngoing()' should return ongoing phone call when there is one and update phone call metadata`() =
        runTest {
            mockkStatic(OngoingPhoneCallDataModel::toDomainModel) {
                // given
                every { ongoingPhoneCallDataSourceMock.get() } returns ongoingPhoneCallDataModel
                coJustRun { phoneCallMetadataDataSourceMock.incrementTimesQueried(id) }
                every { contactNameDataSourceMock.get(phoneNumber) } returns contactName
                every { ongoingPhoneCallDataModel.toDomainModel(contactName) } returns ongoingPhoneCallDomainModel

                // when
                val actualResult = phoneCallRepository.getOngoing()

                // then
                assertEquals(ongoingPhoneCallDomainModel, actualResult)

                coVerify(ordering = Ordering.SEQUENCE) {
                    ongoingPhoneCallDataSourceMock.get()
                    phoneCallMetadataDataSourceMock.incrementTimesQueried(id)
                    contactNameDataSourceMock.get(phoneNumber)
                    ongoingPhoneCallDataModel.toDomainModel(contactName)
                }
                confirmAllMocksVerified()
            }
        }

    @Test
    fun `'getAll()' should return a list of all phone call log entries`() = runTest {
        mockkStatic(PhoneCallLogEntryDataModel::toDomainModel) {
            // given
            val expectedResult = listOf(phoneCallLogEntryDomainModel)

            every { phoneCallLogDataSourceMock.getAll() } returns listOf(phoneCallLogEntryDataModel)
            coEvery { phoneCallMetadataDataSourceMock.get(id) } returns phoneCallMetadataDataModel
            every { contactNameDataSourceMock.get(phoneNumber) } returns contactName
            every {
                phoneCallLogEntryDataModel.toDomainModel(
                    metadata = phoneCallMetadataDataModel,
                    contactName = contactName
                )
            } returns phoneCallLogEntryDomainModel

            // when
            val actualResult = phoneCallRepository.getAll()

            // then
            assertEquals(expectedResult, actualResult)

            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallLogDataSourceMock.getAll()
                phoneCallMetadataDataSourceMock.get(id)
                contactNameDataSourceMock.get(phoneNumber)
                phoneCallLogEntryDataModel.toDomainModel(
                    metadata = phoneCallMetadataDataModel,
                    contactName = contactName
                )
            }
        }
        confirmAllMocksVerified()
    }

    @Test
    fun `'getAll()' should emit a list of all phone call log entries`() = runTest {
        mockkStatic(PhoneCallLogEntryDataModel::toDomainModel) {
            // given
            val expectedResults = listOf(emptyList(), listOf(phoneCallLogEntryDomainModel))

            every {
                phoneCallLogDataSourceMock.getAllRx()
            } returns flowOf(emptyList(), listOf(phoneCallLogEntryDataModel))
            coEvery { phoneCallMetadataDataSourceMock.get(id) } returns phoneCallMetadataDataModel
            every { contactNameDataSourceMock.get(phoneNumber) } returns contactName
            every {
                phoneCallLogEntryDataModel.toDomainModel(
                    metadata = phoneCallMetadataDataModel,
                    contactName = contactName
                )
            } returns phoneCallLogEntryDomainModel

            // when
            phoneCallRepository.getAllRx()
                .test {
                    // then
                    expectedResults.forEach { expectedResult ->
                        assertEquals(expectedResult, awaitItem())
                    }
                    awaitComplete()
                    ensureAllEventsConsumed()

                    coVerify(ordering = Ordering.SEQUENCE) {
                        phoneCallLogDataSourceMock.getAllRx()
                        phoneCallMetadataDataSourceMock.get(id)
                        contactNameDataSourceMock.get(phoneNumber)
                        phoneCallLogEntryDataModel.toDomainModel(
                            metadata = phoneCallMetadataDataModel,
                            contactName = contactName
                        )
                    }
                }
        }
    }

    // endregion

    // region Private Methods

    private fun confirmAllMocksVerified() {
        confirmVerified(
            loggerMock,
            ongoingPhoneCallDataSourceMock,
            phoneCallLogDataSourceMock,
            contactNameDataSourceMock,
            phoneCallMetadataDataSourceMock,
        )
    }

    // endregion

}