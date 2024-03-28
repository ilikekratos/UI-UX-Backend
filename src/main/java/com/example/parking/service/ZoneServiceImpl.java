package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.BaseEntity;
import com.example.parking.models.Zone;
import com.example.parking.repository.LotRepository;
import com.example.parking.repository.SpotRepository;
import com.example.parking.repository.ZoneRepository;
import org.springframework.stereotype.Service;

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
    public boolean deleteZone(Long id) throws NotFoundException {
        if(!zoneRepository.existsZoneByIdIs(id)){
            throw new NotFoundException("Zone not found");
        }
        //Delete spots in zone
        spotRepository.deleteAllByZoneIdIs(id);
        //Delete zones
        zoneRepository.deleteZoneByIdIs(id);
        return true;
    }

    @Override
    public boolean addZone(Long lotId, Long length, Long width) {
        if(lotRepository.existsLotByIdIs(lotId)){
            try{
                var newZone=new Zone(lotId,length,width);
                zoneRepository.save(newZone);
                return true;
            }
            catch(Exception e)
            {return false;}
        }
        return false;
    }


}
