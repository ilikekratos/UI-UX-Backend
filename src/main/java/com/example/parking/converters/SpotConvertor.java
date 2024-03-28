package com.example.parking.converters;

import com.example.parking.dtos.SpotDTO;
import com.example.parking.dtos.UserDTO;
import com.example.parking.models.Spot;
import com.example.parking.models.User;
import org.springframework.stereotype.Component;

@Component
public class SpotConvertor extends BaseConvertor<Long, Spot, SpotDTO>{
    @Override
    public Spot convertDtoToModel(SpotDTO spotDTO){
        return new Spot(spotDTO.getOccupiedId(), spotDTO.getZoneId());
    }

    @Override
    public SpotDTO convertModelToDto(Spot spot) {
        var spotDto= new SpotDTO(spot.getOccupiedId(), spot.getZoneId());
        spotDto.setId(spot.getId());
        return spotDto;
    }
}
