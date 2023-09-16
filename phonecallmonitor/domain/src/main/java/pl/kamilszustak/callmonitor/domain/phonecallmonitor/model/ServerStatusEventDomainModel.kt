package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

sealed interface ServerStatusEventDomainModel {
    data object Started : ServerStatusEventDomainModel
    data object Stopped : ServerStatusEventDomainModel
}