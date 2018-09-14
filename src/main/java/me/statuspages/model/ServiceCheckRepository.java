package me.statuspages.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceCheckRepository {
    List<ServiceCheck> findByServiceIdOrderByName(String serviceId);

    Optional<ServiceCheck> findByServiceIdAndName(String serviceId, String name);

//    @Query(value = "SELECT id FROM service_check WHERE service_id = ?1", nativeQuery = true)
    Set<String> findIdByServiceId(String serviceId);

//    @Modifying
    int deleteByServiceId(String serviceId);

//    @Query(value = "SELECT * FROM service_check" +
//            " WHERE service_id IN (SELECT id FROM service WHERE visibility = ?1)" +
//            " ORDER by service_id, name", nativeQuery = true)
    List<ServiceCheck> findByServiceVisibilityOrderByServiceIdAndName(String serviceVisibility);
}
