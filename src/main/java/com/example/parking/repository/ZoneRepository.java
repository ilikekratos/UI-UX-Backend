package com.example.parking.repository;

import com.example.parking.models.User;
import com.example.parking.models.Zone;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ZoneRepository extends RepositoryBase<Zone,Long>{
    //List<Zone> findAllByCompany_idIs(String company_id);
}
