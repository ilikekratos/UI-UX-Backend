package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.BaseEntity;
import com.example.parking.models.Zone;
import com.example.parking.repository.LotRepository;
import com.example.parking.repository.SpotRepository;
import com.example.parking.repository.ZoneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ZoneServiceImpl implements ZoneService{
    private final ZoneRepository zoneRepository;
    private final SpotRepository spotRepository;
    private final LotRepository lotRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository, SpotRepository spotRepository, LotRepository lotRepository) {
        this.zoneRepository = zoneRepository;
        this.spotRepository = spotRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public List<Zone> getAllByLotId(Long id) throws NotFoundException {
        if(!lotRepository.existsLotByIdIs(id)){
            throw new NotFoundException("Lot not found");
        }
        return zoneRepository.findAllByLotIdIs(id);
    }

    @Override
    @Transactional
    public boolean deleteZone(Long id) throws NotFoundException {
        if(!zoneRepository.existsZoneByIdIs(id)){
            throw new NotFoundException("Zone not found");
        }
        //Delete spots in zone
        if(spotRepository.existsSpotByZoneIdIs(id)){
            spotRepository.deleteAllByZoneIdIs(id);
        }
        //Delete zones
        zoneRepository.deleteZoneByIdIs(id);
        return true;
    }

    @Override
    public boolean addZone(Long lotId, String zoneName) {
        if(lotRepository.existsLotByIdIs(lotId)){
            try{
                var newZone=new Zone(lotId,zoneName);
                zoneRepository.save(newZone);
                return true;
            }
            catch(Exception e)
            {return false;}
        }
        return false;
    }

    @Override
    public boolean editZone(Long zoneId, String zoneName) {
        if(zoneRepository.existsZoneByIdIs(zoneId)){
            int updatedRows = zoneRepository.updateZoneNameById(zoneId, zoneName);
            if (updatedRows == 0) {
                throw new EntityNotFoundException("Zone with id " + zoneId + " not found.");
            }
            return true;
        }
        return false;
    }


}
