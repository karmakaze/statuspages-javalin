package me.statuspages.model

import org.jdbi.v3.sqlobject.statement.SqlQuery

interface ServiceDao {
    @SqlQuery("SELECT * FROM service WHERE visibility = :visibility ORDER BY id")
    fun listByVisbility(visibility: String): List<Service>

    @SqlQuery("SELECT * FROM service WHERE id = :id")
    fun fetch(id: String): Service?
}
