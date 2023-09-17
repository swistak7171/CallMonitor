package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

interface OngoingPhoneCallDataSource {
    fun get(): OngoingPhoneCallDataModel?
    fun setStarted(model: OngoingPhoneCallDataModel)
    fun setEnded()
}