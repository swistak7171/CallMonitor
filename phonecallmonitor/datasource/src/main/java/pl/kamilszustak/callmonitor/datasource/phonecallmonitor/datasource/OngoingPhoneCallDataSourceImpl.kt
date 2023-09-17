package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    // region Fields

    private var ongoingPhoneCall: OngoingPhoneCallDataModel? = null

    // endregion

    // region OngoingPhoneCallDataSource Implementation

    override fun get(): OngoingPhoneCallDataModel? {
        return ongoingPhoneCall
    }

    override fun setStarted(model: OngoingPhoneCallDataModel) {
        ongoingPhoneCall = model
    }

    override fun setEnded() {
        ongoingPhoneCall = null
    }

    // endregion

}