package com.example.parking.repository;

import com.example.parking.models.Zone;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ZoneRepository extends RepositoryBase<Zone,Long>{
    List<Zone> findAllByLotIdIs(Long lotId);
    void deleteZonesByLotIdIs(Long id);
}
