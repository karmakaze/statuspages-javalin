package me.statuspages.model

import java.time.Instant

data class ServiceCheckStatus (
    val serviceCheckId: String,
    val createdAt: Instant,

    val state: String,
    val status: String,
    val text: String
)
