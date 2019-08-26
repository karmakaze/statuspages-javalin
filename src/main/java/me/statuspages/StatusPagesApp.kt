package me.statuspages;

import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import me.statuspages.api.ServiceCheckController
import me.statuspages.api.ServiceController
import me.statuspages.db.Database
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule


fun main(args: Array<String>) {
    {
        val objectMapper = ObjectMapper()
        val javaTimeModule = JavaTimeModule()
        objectMapper.registerModule(javaTimeModule)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JavalinJackson.configure(objectMapper)
    }()

    val app = Javalin.create().start(7000)

    val db = Database.fromEnvVar()

    val serviceController = ServiceController(db.jdbi)
    val serviceCheckController = ServiceCheckController(db.jdbi)

    app.get("/") { ctx ->
        ctx.result("Hello World")
    }

    // services
    app.get("/services") { ctx -> serviceController.list(ctx) }
    app.get("/services/:id") { ctx -> serviceController.fetch(ctx) }

    app.post("/services") { ctx -> serviceController.create(ctx) }
    app.put("/services/:id") { ctx -> serviceController.update(ctx) }
    app.delete("/services/:id") { ctx -> serviceController.delete(ctx) }

    // service checks
    app.get("/services/:service_id/checks") { ctx -> serviceCheckController.list(ctx) }
    app.get("/services/:service_id/checks/:name/status") { ctx -> serviceCheckController.status(ctx) }
    app.get("/services/:service_id/checks/*/status") { ctx -> serviceCheckController.latestStatuses(ctx) }

    app.post("/services/:service_id/checks") { ctx -> serviceCheckController.create(ctx) }
    app.post("/services/:service_id/webhooks/receive/events") { ctx -> serviceCheckController.receiveWebhookUpdate(ctx) }
}
