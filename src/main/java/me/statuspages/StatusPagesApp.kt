package me.statuspages;

import io.javalin.Javalin
import me.statuspages.db.Database
import me.statuspages.db.TodoDAO

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)

    val db = Database.fromEnvVar()

    val todoDao = db.getDao(TodoDAO::class.java)

    app.get("/") { ctx ->
        ctx.result("Hello World")
    }
}
