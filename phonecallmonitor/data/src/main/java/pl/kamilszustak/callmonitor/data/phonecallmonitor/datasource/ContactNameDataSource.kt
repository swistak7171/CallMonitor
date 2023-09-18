package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

/**
 * A data source for retrieving contact names associated with phone numbers.
 */
interface ContactNameDataSource {

    /**
     * Returns the contact name associated with the specified [phoneNumber] or `null` if no match
     * is found.
     */
    fun get(phoneNumber: String): String?
}