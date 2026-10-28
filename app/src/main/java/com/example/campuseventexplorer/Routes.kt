package com.example.campuseventexplorer

import kotlinx.serialization.Serializable

/** Type-safe route for the start destination: the event list. */
@Serializable
data object EventList

/**
 * Type-safe route for the detail destination.
 * It carries only the event's identity (its id), never the whole [CampusEvent].
 */
@Serializable
data class EventDetail(
    val eventId: Long
)
