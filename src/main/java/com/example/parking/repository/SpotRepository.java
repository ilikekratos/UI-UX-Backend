package com.example.parking.repository;

import com.example.parking.models.Spot;
import com.example.parking.models.Zone;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpotRepository extends RepositoryBase<Spot,Long>{
    //List<Spot> findAllByZone_idIs(Long zone_id);
}
