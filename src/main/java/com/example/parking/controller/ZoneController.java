package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.ZoneConvertor;
import com.example.parking.dtos.*;
import com.example.parking.exceptions.NotFoundException;
import com.example.parking.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {

    @Autowired
    private ZoneConvertor zoneConvertor;
    @Autowired
    private ZoneService zoneService;

    @GetMapping(value = "zone/getAllByLot")
    public ResponseEntity<List<ZoneDTO>> getAllZones(@RequestBody RequestWrapper<Long> requestWrapper) throws NotFoundException {
        if(verifyToken(requestWrapper.getTokenDTO().getToken())) {
            try{
            List<ZoneDTO> temp = zoneService.getAllByLotId(requestWrapper.getDto()).stream().map(zone -> zoneConvertor.convertModelToDto(zone)).toList();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
            }
            catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PostMapping(value = "zone/add")
    public ResponseEntity<String> addZone(@RequestBody RequestWrapper<ZoneDTO> requestWrapper){
        if(verifyToken(requestWrapper.getTokenDTO().getToken())) {
            var zone= zoneConvertor.convertDtoToModel(requestWrapper.getDto());
            if(zoneService.addZone(zone.getLotId(),zone.getLength(),zone.getWidth())){
                return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }
    @DeleteMapping(value = "zone/delete")
    public ResponseEntity<String> deleteZone(@RequestBody RequestWrapper<Long> requestWrapper){
        if(verifyToken(requestWrapper.getTokenDTO().getToken())) {
            try{
                if(zoneService.deleteZone(requestWrapper.getDto())){
                    return ResponseEntity.status(HttpStatus.OK).body("Successful");
                }
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
            }
            catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed");
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SERVER ERROR");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }
    boolean verifyToken(String token){
        return JwtConfig.verifyToken(token);
    }
}
