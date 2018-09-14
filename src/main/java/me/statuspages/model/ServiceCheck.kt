package me.statuspages.model

import java.time.Instant

data class ServiceCheck (
    val id: String,

    val serviceId: String,
    val name: String,
    val statusPageUrl: String,
    val statusDataUrl: String,

    val selector: String?,
    val mapper: String?,
    val matchOkay: String?,
    val warnSelector: String?,
    val matchWarn: String?,
    val downSelector: String?,
    val matchDown: String?,

    val script: String?,

    val updatedAt: Instant
)
{
    fun hasMatchOkayScript(): Boolean {
        return matchOkay != null && matchOkay.startsWith("function")
    }

    fun hasMatchWarnScript(): Boolean {
        return matchWarn != null && matchWarn.startsWith("function")
    }

    fun hasMatchDownScript(): Boolean {
        return matchDown != null && matchDown.startsWith("function")
    }
}
