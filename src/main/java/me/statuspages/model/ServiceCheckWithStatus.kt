package me.statuspages.model

import java.time.Instant

data class ServiceCheckWithStatus (
    private val serviceId: String,
    private val name: String,

    private val statusDataUrl: String,
    private val statusPageUrl: String,

    private val createdAt: Instant,
    private val state: String,
    private val status: String,
    private val text: String
)
