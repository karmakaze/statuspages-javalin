package me.statuspages.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceCheckStatusRepository {
//    @Query(value = "SELECT DISTINCT ON(scs.name, scs.created_at) scs.*" +
//            " FROM service_check sc" +
//            " JOIN service_check_status scs ON sc.id = scs.service_check_id" +
//            " WHERE sc.service_id = ?1" +
//            " ORDER BY scs.name DESC, scs.created_at DESC", nativeQuery = true)
    List<ServiceCheckStatus> findByServiceIdLatestByCheckName(String serviceId);

    Optional<ServiceCheckStatus> findFirstByIdServiceCheckIdOrderByIdCreatedAtDesc(String serviceCheckId);

    int deleteByIdServiceCheckIdIn(Set<String> serviceCheckIds);
}
