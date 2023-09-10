package pl.kamilszustak.callmonitor.datasource

import android.content.Context
import android.provider.ContactsContract

class ContactNameDataSourceImpl(
    private val context: Context,
) : ContactNameDataSource {

    override fun get(phoneNumber: String): String? {
        val contentResolver = context.contentResolver
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            "${ContactsContract.CommonDataKinds.Phone.NUMBER} = ?",
            arrayOf(phoneNumber),
            null
        )

        return if (cursor != null && cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val name = cursor.getString(nameIndex)
            cursor.close()

            name
        } else {
            null
        }
    }

}