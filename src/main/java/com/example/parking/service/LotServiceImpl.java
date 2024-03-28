package com.example.parking.service;

import com.example.parking.models.Lot;
import com.example.parking.repository.LotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotServiceImpl implements LotService{

    private final LotRepository lotRepository;

    public LotServiceImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public List<Lot> getAll() {
        return lotRepository.findAllByIdIsNotNull();
    }

    @Override
    public void addLot(String lot_name, double latitude, double longitude) {

        if(!lotRepository.existsLotByLot_nameIs(lot_name)){
            var lot = new Lot(lot_name,latitude,longitude);
            lotRepository.save(lot);
        }
    }

    @Override
    public boolean checkLot_name(String name) {
        return(lotRepository.existsLotByLot_nameIs(name));
    }
}
