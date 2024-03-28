package com.example.parking.service;

import com.example.parking.models.BaseEntity;
import com.example.parking.models.Lot;
import com.example.parking.repository.LotRepository;
import com.example.parking.repository.SpotRepository;
import com.example.parking.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotServiceImpl implements LotService{

    private final LotRepository lotRepository;
    private final ZoneRepository zoneRepository;
    private final SpotRepository spotRepository;
    public LotServiceImpl(LotRepository lotRepository, ZoneRepository zoneRepository, SpotRepository spotRepository) {
        this.lotRepository = lotRepository;
        this.zoneRepository = zoneRepository;
        this.spotRepository = spotRepository;
    }

    @Override
    public List<Lot> getAll() {
        return lotRepository.findAllByIdIsNotNull();
    }

    @Override
    public void addLot(String lotName, double latitude, double longitude) {

        if(!lotRepository.existsLotByLotNameIs(lotName)){
            var lot = new Lot(lotName,latitude,longitude);
            lotRepository.save(lot);
        }
    }

    @Override
    public boolean checkLot_name(String name) {
        return(lotRepository.existsLotByLotNameIs(name));
    }

    @Override
    public boolean deleteLot(Long id) {
        if(lotRepository.existsLotByIdIs(id)){
            //Delete spots in zones which are in lot
            List<Long> zoneIds=zoneRepository.findAllByLotIdIs(id).stream().map(BaseEntity::getId).toList();
            spotRepository.deleteAllByZoneIdIsIn(zoneIds);
            //Delete zones that are in lot
            zoneRepository.deleteZonesByLotIdIs(id);
            //Delete lot
            lotRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
