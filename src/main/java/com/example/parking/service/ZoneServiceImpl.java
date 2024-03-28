package com.example.parking.service;

import com.example.parking.models.Zone;
import com.example.parking.repository.ZoneRepository;

import java.util.List;

public class ZoneServiceImpl implements ZoneService{
    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public List<Zone> getAll(Long id) {
        return zoneRepository.findAllByLotIdIs(id);
    }
}
