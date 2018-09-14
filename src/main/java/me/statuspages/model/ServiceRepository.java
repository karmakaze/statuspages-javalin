package me.statuspages.model;

import java.util.List;

public interface ServiceRepository {
//    @Query(value = "SELECT * FROM service WHERE visibility = ?1 ORDER BY id", nativeQuery = true)
    List<Service> findByVisbility(String visibility);
}
