package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel

/**
 * A data source for capturing phone call events and providing them.
 *
 * This class listens for phone call events using a BroadcastReceiver and emits relevant call
 * information, such as call start and end events, as instances of [PhoneCallEventDataModel].
 */
interface PhoneCallEventDataSource {

    /**
     * Returns a [Flow] of [PhoneCallEventDataModel] representing phone call events, including call
     * start and end events.
     */
    fun getRx(): Flow<PhoneCallEventDataModel>
}