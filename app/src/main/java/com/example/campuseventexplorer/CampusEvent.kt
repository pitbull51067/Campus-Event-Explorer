package com.example.campuseventexplorer

/**
 * One campus event. [id] is unique and stable: it is hard-coded in [sampleEvents],
 * never generated from a list position, so it can safely be used in routes and LazyColumn keys.
 */
data class CampusEvent(
    val id: Long,
    val title: String,
    val category: String,
    val location: String,
    val time: String,
    val description: String
)
