package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.content.Context
import android.provider.ContactsContract
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ContactNameDataSource

/**
 * A data source for retrieving contact names associated with phone numbers.
 *
 * This class provides a way to query the device's contact database and retrieve the display name
 * associated with a given phone number.
 */
internal class ContactNameDataSourceImpl(
    /**
     * An instance of [Context] used for accessing content providers.
     */
    private val context: Context,
) : ContactNameDataSource {

    // region ContactNameDataSource Implementation

    override fun get(phoneNumber: String): String? {
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val cursor = context.contentResolver.query(
            /* uri = */ ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            /* projection = */ projection,
            /* selection = */ "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
            /* selectionArgs = */ arrayOf(phoneNumber),
            /* sortOrder = */ null
        )

        return if (cursor != null && cursor.moveToFirst()) {
            val index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val name = cursor.getString(index)
            cursor.close()

            name
        } else {
            null
        }
    }

    // endregion

}