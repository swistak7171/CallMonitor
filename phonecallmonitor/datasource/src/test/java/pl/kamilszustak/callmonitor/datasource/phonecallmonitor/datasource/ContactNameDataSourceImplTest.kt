package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.contactName
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneNumber
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ContactNameDataSourceImplTest {

    // region Fields

    private val contextMock: Context = mockk()

    private val contactNameDataSource: ContactNameDataSourceImpl = ContactNameDataSourceImpl(
        context = contextMock
    )

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(contextMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'get()' should return a contact name when a contact can be found by phone number`() {
        // given
        val columnIndex = 1000
        val cursorMock = mockk<Cursor>()

        every {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
        } returns cursorMock
        every { cursorMock.moveToFirst() } returns true
        every {
            cursorMock.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        } returns columnIndex
        every { cursorMock.getString(columnIndex) } returns contactName
        justRun { cursorMock.close() }

        // when
        val actualResult = contactNameDataSource.get(phoneNumber)

        // then
        assertEquals(contactName, actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
            cursorMock.moveToFirst()
            cursorMock.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            cursorMock.getString(columnIndex)
            cursorMock.close()
        }
        confirmVerified(cursorMock)
    }

    @Test
    fun `'get()' should return 'null' when ContentResolver does not return a Cursor`() {
        // given
        every {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
        } returns null

        // when
        val actualResult = contactNameDataSource.get(phoneNumber)

        // then
        assertNull(actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
        }
    }

    @Test
    fun `'get()' should return 'null' when Cursor is empty`() {
        // given
        val cursorMock = mockk<Cursor>()

        every {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
        } returns cursorMock
        every { cursorMock.moveToFirst() } returns false

        // when
        val actualResult = contactNameDataSource.get(phoneNumber)

        // then
        assertNull(actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            contextMock.contentResolver.query(
                /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                /* projection = */ arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
                /* selectionArgs = */ arrayOf(phoneNumber),
                /* sortOrder = */ null
            )
            cursorMock.moveToFirst()
        }
        confirmVerified(cursorMock)
    }

    // endregion

}