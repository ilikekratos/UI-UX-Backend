package com.example.parking.service;

import com.example.parking.exceptions.NotFoundException;
import com.example.parking.models.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAllByLotId(Long id) throws NotFoundException;
    boolean deleteZone(Long id) throws NotFoundException;
    boolean addZone(Long lotId,String zoneName);
    boolean editZone(Long zoneId,String zoneName);
}
