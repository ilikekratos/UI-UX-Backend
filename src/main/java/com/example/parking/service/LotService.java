package com.example.parking.service;

import com.example.parking.models.Lot;

import java.util.List;

public interface LotService {
    List<Lot> getAll();
    void addLot(String lot_name,double latitude,double longitude);
    boolean checkLot_name(String name);
}
