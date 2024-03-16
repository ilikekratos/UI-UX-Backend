package com.example.parking.repository;

import com.example.parking.models.User;
import com.example.parking.models.Zone;

import java.util.List;

public interface ZoneRepository extends RepositoryBase<Zone,Long>{
    List<Zone> findByCompany_idIs(String company_id);
}
