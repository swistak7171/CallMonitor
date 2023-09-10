package pl.kamilszustak.callmonitor.datasource

interface ContactNameDataSource {
    fun get(phoneNumber: String): String?
}