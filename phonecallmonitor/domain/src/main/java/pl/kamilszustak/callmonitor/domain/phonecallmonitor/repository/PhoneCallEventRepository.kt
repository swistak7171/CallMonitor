package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

/**
 * A repository for phone call events.
 */
interface PhoneCallEventRepository {

    /**
     * Returns a [Flow] of phone call events represented by [PhoneCallEventDomainModel].
     */
    fun getRx(): Flow<PhoneCallEventDomainModel>
}