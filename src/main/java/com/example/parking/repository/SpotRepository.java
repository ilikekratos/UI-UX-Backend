package com.example.parking.repository;

import com.example.parking.models.Spot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends RepositoryBase<Spot,Long>{
    void deleteAllByZoneIdIsIn(List<Long> ids);
    void deleteAllByZoneIdIs(Long id);
    //List<Spot> findAllByZone_idIs(Long zone_id);
    boolean existsSpotByIdIs(Long id);
    boolean existsSpotByZoneIdIs(Long id);
    boolean existsSpotByOccupiedIdIs(Long id);
    Optional<Spot> findByOccupiedIdIs(Long id);
    List<Spot> findAllByZoneIdIs(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Spot s SET s.occupiedId = :occupiedId WHERE s.id = :id")
    void updateSpotOccupiedIdById(@Param("id") Long id, @Param("occupiedId") Long occupiedId);
}
