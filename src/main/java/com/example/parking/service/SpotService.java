package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.Spot;

import java.util.List;

public interface SpotService {
    boolean deleteSpot(Long id) throws NotFoundException;
    boolean addSpot(Long zoneId);
    boolean updateSpot(Long occupiedId,Long spotId);
    boolean clearSpot(Long occupiedId,Long spotId);

    List<Spot> getAllByZone(Long id)throws NotFoundException;
}
