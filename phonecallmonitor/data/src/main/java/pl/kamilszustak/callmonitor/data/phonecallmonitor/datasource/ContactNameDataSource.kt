package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

interface ContactNameDataSource {
    fun get(phoneNumber: String): String?
}