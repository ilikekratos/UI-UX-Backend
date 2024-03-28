package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.Spot;
import com.example.parking.models.Zone;
import com.example.parking.repository.SpotRepository;
import com.example.parking.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpotServiceImpl implements SpotService{
    private final SpotRepository spotRepository;
    private final ZoneRepository zoneRepository;

    public SpotServiceImpl(SpotRepository spotRepository, ZoneRepository zoneRepository) {
        this.spotRepository = spotRepository;
        this.zoneRepository = zoneRepository;
    }
    @Override
    public boolean deleteSpot(Long id) throws NotFoundException {
        if(!spotRepository.existsSpotByIdIs(id)){
            throw new NotFoundException("Zone not found");
        }
        spotRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean addSpot(Long zoneId) {
        Optional<Zone> optionalZone = zoneRepository.findZoneByIdIs(zoneId);
        if (optionalZone.isPresent()) {
            Zone zone = optionalZone.get();
            if (spotRepository.findAllByZoneIdIs(zoneId).toArray().length < zone.getLength() * zone.getWidth()) {
                Spot spot = new Spot(null, zoneId);
                spotRepository.save(spot);
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public boolean updateSpot(Long occupiedId,Long spotId) {
        Optional<Spot> optionalSpot=spotRepository.findById(spotId);
        if(optionalSpot.isPresent()){
            Spot spot=optionalSpot.get();
            spot.setOccupiedId(occupiedId);
            spotRepository.save(spot);
            return true;
        }
        return false;
    }

    @Override
    public boolean clearSpot(Long occupiedId, Long spotId) {
        Optional<Spot> optionalSpot=spotRepository.findById(spotId);
        if(optionalSpot.isPresent()){
            Spot spot=optionalSpot.get();
            spot.setOccupiedId(null);
            spotRepository.save(spot);
            return true;
        }
        return false;
    }

    @Override
    public List<Spot> getAllByZone(Long id) throws NotFoundException {
        if(!zoneRepository.existsById(id)){
            throw new NotFoundException("Zone not found");
        }
        return spotRepository.findAllByZoneIdIs(id);
    }


}





