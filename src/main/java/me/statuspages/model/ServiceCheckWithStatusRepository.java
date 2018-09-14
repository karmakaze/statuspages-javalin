package me.statuspages.model;

import java.util.List;

public interface ServiceCheckWithStatusRepository {
//    @Query(value = "SELECT DISTINCT ON(service_id, name, status_page_url) *" +
//            " FROM service_check_with_status" +
//            " WHERE service_id = ?1" +
//            " ORDER BY service_id DESC, name DESC, status_page_url DESC, created_at DESC", nativeQuery = true)
    List<ServiceCheckWithStatus> findByServiceIdLatestByName(String serviceId);
}
