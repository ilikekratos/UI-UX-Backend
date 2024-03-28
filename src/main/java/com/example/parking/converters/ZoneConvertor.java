package com.example.parking.converters;

import com.example.parking.dtos.UserDTO;
import com.example.parking.dtos.ZoneDTO;
import com.example.parking.models.Zone;
import org.springframework.stereotype.Component;

@Component
public class ZoneConvertor extends BaseConvertor<Long, Zone, ZoneDTO>{
    @Override
    public Zone convertDtoToModel(ZoneDTO dto) {
        return new Zone(dto.getLotId(), dto.getLength(),dto.getWidth());
    }

    @Override
    public ZoneDTO convertModelToDto(Zone zone) {
        var zoneDto= new ZoneDTO(zone.getLotId(), zone.getLength(),zone.getWidth());
        zoneDto.setId(zone.getId());
        return zoneDto;
    }
}
