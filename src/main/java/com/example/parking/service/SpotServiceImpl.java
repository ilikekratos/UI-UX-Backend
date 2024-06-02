package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.Spot;
import com.example.parking.models.User;
import com.example.parking.models.Zone;
import com.example.parking.repository.SpotRepository;
import com.example.parking.repository.UserRepository;
import com.example.parking.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpotServiceImpl implements SpotService{
    private final SpotRepository spotRepository;
    private final ZoneRepository zoneRepository;
    private final UserRepository userRepository;

    public SpotServiceImpl(SpotRepository spotRepository, ZoneRepository zoneRepository, UserRepository userRepository) {
        this.spotRepository = spotRepository;
        this.zoneRepository = zoneRepository;
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
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
            Spot spot = new Spot((long) -1, zoneId);
            spotRepository.save(spot);
            return true;

        }
        return false;
    }
    @Override
    public boolean updateSpot(String username, Long spotId) {

        Optional<Spot> optionalSpot=spotRepository.findById(spotId);
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalSpot.isPresent() && optionalUser.isPresent()){
            spotRepository.updateSpotOccupiedIdById(spotId,optionalUser.get().getId());
            return true;
        }
        return false;
    }
    @Override
    public boolean clearSpotByUser(String username){
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent() ){
            Optional<Spot> optionalSpot = spotRepository.findByOccupiedIdIs(optionalUser.get().getId());
            if(optionalSpot.isPresent()){
                spotRepository.updateSpotOccupiedIdById(optionalSpot.get().getId(),(long)-1);
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public boolean existSpotOccupied(String username){
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent() ){

            Optional<Spot> optionalSpot = spotRepository.findByOccupiedIdIs(optionalUser.get().getId());
            return optionalSpot.isPresent();
        }
        return false;
    }
    @Override
    @Transactional
    public boolean clearSpot( Long spotId) {
        Optional<Spot> optionalSpot=spotRepository.findById(spotId);
        if(optionalSpot.isPresent()){
            spotRepository.updateSpotOccupiedIdById(spotId,(long)-1);
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





