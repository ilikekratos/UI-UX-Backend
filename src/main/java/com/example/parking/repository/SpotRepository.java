package com.example.parking.repository;

import com.example.parking.models.Spot;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpotRepository extends RepositoryBase<Spot,Long>{
    void deleteAllByZoneIdIsIn(List<Long> ids);
    void deleteAllByZoneIdIs(Long id);
    //List<Spot> findAllByZone_idIs(Long zone_id);
    boolean existsSpotByIdIs(Long id);

    boolean existsSpotByOccupiedIdIs(Long id);

    List<Spot> findAllByZoneIdIs(Long id);
}
