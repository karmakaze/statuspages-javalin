package me.statuspages.model

import org.jdbi.v3.sqlobject.statement.SqlQuery

interface ServiceCheckDao {
    @SqlQuery("SELECT * FROM service_check WHERE service_id = :serviceId ORDER BY name")
    fun listByServiceIdOrderByName(serviceId: String): List<ServiceCheck>

    @SqlQuery("SELECT * FROM service_check WHERE service_id = :serviceId AND name = :name")
    fun findByServiceIdAndName(serviceId: String, name: String): ServiceCheck?

    @SqlQuery("SELECT id FROM service_check WHERE service_id = :serviceId")
    fun listIdByServiceId(serviceId: String): Set<String>

//    @Modifying
//    abstract fun deleteByServiceId(serviceId: String): Int

    @SqlQuery("SELECT * FROM service_check" +
            " WHERE service_id IN (SELECT id FROM service WHERE visibility = :serviceVisibility)" +
            " ORDER by service_id, name")
    fun listByServiceVisibilityOrderByServiceIdAndName(serviceVisibility: String): List<ServiceCheck>
}
