package com.example.parking.repository;

import com.example.parking.models.Zone;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends RepositoryBase<Zone,Long>{
    List<Zone> findAllByLotIdIs(Long lotId);
    void deleteZonesByLotIdIs(Long id);
    void deleteZoneByIdIs(Long id);
    boolean existsZoneByIdIs(Long id);
    Optional<Zone> findZoneByIdIs(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Zone z SET z.zoneName = :zoneName WHERE z.id = :id")
    int updateZoneNameById(@Param("id") Long id, @Param("zoneName") String zoneName);
}
