package com.example.parking.converters;

import com.example.parking.dtos.LotDTO;
import com.example.parking.models.Lot;
import org.springframework.stereotype.Component;

@Component
public class LotConvertor extends BaseConvertor<Long, Lot, LotDTO>{

    @Override
    public Lot convertDtoToModel(LotDTO dto) {
        return new Lot(dto.getLotName(), dto.getLatitude(), dto.getLongitude());
    }

    @Override
    public LotDTO convertModelToDto(Lot lot) {
        var lotDTO= new LotDTO(lot.getLotName(), lot.getLatitude(), lot.getLongitude());
        lotDTO.setId(lot.getId());
        return lotDTO;
    }
}
