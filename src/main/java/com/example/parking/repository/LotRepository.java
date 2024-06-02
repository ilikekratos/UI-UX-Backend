package com.example.parking.repository;

import com.example.parking.models.Lot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LotRepository extends RepositoryBase<Lot,Long>{
    List<Lot> findAllByIdIsNotNull();
    boolean existsLotByLotNameIs(String name);

    boolean existsLotByIdIs(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Lot l SET l.lotName = :lotName WHERE l.id = :id")
    int updateLotNameById(@Param("id") Long id, @Param("lotName") String lotName);
}
