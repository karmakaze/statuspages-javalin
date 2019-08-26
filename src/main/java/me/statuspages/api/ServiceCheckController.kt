package me.statuspages.api

import io.javalin.Context
import me.statuspages.model.ServiceCheckDao
import me.statuspages.model.ServiceDao
import org.eclipse.jetty.http.HttpStatus
import org.jdbi.v3.core.Jdbi
import org.slf4j.LoggerFactory

data class ServiceCheckController(val jdbi: Jdbi) {
    val log = LoggerFactory.getLogger(ServiceCheckController::class.java)

    val serviceDao = jdbi.onDemand(ServiceDao::class.java)
    val dao = jdbi.onDemand(ServiceCheckDao::class.java)

    fun list(ctx: Context) {
        val serviceId = ctx.pathParam("service_id")
        val service = serviceDao.fetch(serviceId)
        if (service == null) {
            ctx.status(HttpStatus.NOT_FOUND_404)
        } else {
            val serviceChecks = dao.listByServiceIdOrderByName(serviceId)
            ctx.json(serviceChecks)
        }
    }

    fun create(ctx: Context) {
        ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
    }

    fun status(ctx: Context) {
        val serviceId = ctx.pathParam("service_id")
        val name = ctx.pathParam("name")
        log.info("GET /services/{}/checks/{}", serviceId, name)

        val serviceCheck = dao.findByServiceIdAndName(serviceId, name)
        if (serviceCheck == null) {
            ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
        } else {
            ctx.json(serviceCheck)
        }
    }

    fun latestStatuses(ctx: Context) {
        val serviceId = ctx.pathParam("service_id")
        val service = serviceDao.fetch(serviceId)
        if (service == null) {
            ctx.status(HttpStatus.NOT_FOUND_404)
        } else {
            val serviceChecks = dao.listByServiceIdOrderByName(serviceId)
            ctx.json(serviceChecks)
        }
    }

    fun receiveWebhookUpdate(ctx: Context) {
        ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
    }
}
