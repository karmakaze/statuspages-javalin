package me.statuspages.api

import io.javalin.Context
import me.statuspages.model.ServiceDao
import me.statuspages.model.Visibility
import org.eclipse.jetty.http.HttpStatus
import org.jdbi.v3.core.Jdbi
import org.slf4j.LoggerFactory

data class ServiceController(val jdbi: Jdbi) {
    val log = LoggerFactory.getLogger(ServiceController::class.java)
    val dao = jdbi.onDemand(ServiceDao::class.java)

    fun list(ctx: Context) {
        val services = dao.listByVisbility(ctx.queryParam("visibility", Visibility.PUBLIC.name)!!)
        log.info("Queried {} services", services.size)
        ctx.json(services)
    }

    fun fetch(ctx: Context) {
        val id = ctx.pathParam("id")
        val service = dao.fetch(id)
        if (service == null) {
            ctx.status(HttpStatus.NOT_FOUND_404)
        } else {
            ctx.json(service)
        }
    }

    fun create(ctx: Context) {
        ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
    }

    fun update(ctx: Context) {
        ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
    }

    fun delete(ctx: Context) {
        ctx.status(HttpStatus.NOT_IMPLEMENTED_501)
    }
}
