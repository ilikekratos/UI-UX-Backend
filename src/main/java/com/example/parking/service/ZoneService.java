package com.example.parking.service;

import com.example.parking.models.Zone;

import java.util.List;

public interface ZoneService {
    List<Zone> getAll(Long id);
}
