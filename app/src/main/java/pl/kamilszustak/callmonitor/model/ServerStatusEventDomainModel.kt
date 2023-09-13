package pl.kamilszustak.callmonitor.model

sealed interface ServerStatusEventDomainModel {
    data object Started : ServerStatusEventDomainModel
    data object Stopped : ServerStatusEventDomainModel
}