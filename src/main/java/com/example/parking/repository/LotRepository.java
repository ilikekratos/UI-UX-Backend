package com.example.parking.repository;

import com.example.parking.models.Lot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends RepositoryBase<Lot,Long>{
    List<Lot> findAllByIdIsNotNull();
    boolean existsLotByLotNameIs(String name);

    boolean existsLotByIdIs(Long id);
}
