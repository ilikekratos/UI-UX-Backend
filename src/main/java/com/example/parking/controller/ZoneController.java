package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.ZoneConvertor;
import com.example.parking.dtos.*;
import com.example.parking.exceptions.NotFoundException;
import com.example.parking.service.ZoneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ZoneController {

    @Autowired
    private ZoneConvertor zoneConvertor;
    @Autowired
    private ZoneService zoneService;

    @GetMapping(value = "zone/getAllByLot/{id}")
    public ResponseEntity<List<ZoneDTO>> getAllZones(@RequestHeader("Authorization") String token, @PathVariable("id") Long lotId) throws NotFoundException {
        if(verifyToken(token)) {
            try{
            List<ZoneDTO> temp = zoneService.getAllByLotId(lotId).stream().map(zone -> zoneConvertor.convertModelToDto(zone)).toList();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
            }
            catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PostMapping(value = "zone/add")
    public ResponseEntity<String> addZone(@RequestHeader("Authorization") String token,@RequestBody ZoneDTO zoneDTO){
        if(verifyToken(token)){
            var zone= zoneConvertor.convertDtoToModel(zoneDTO);
            if(zoneService.addZone(zone.getLotId(),zone.getZoneName())){
                return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }
    @DeleteMapping(value = "zone/delete/{id}")
    public ResponseEntity<String> deleteZone(@RequestHeader("Authorization") String token,@PathVariable("id") Long zoneId) throws NotFoundException{
        if(verifyToken(token)){
            try{
                if(zoneService.deleteZone(zoneId)){
                    return ResponseEntity.status(HttpStatus.OK).body("Successful");
                }
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
            }
            catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SERVER ERROR");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }
    @PutMapping(value = "zone/edit/{id}")
    public ResponseEntity<String> editZoneName(@RequestHeader("Authorization") String token,@PathVariable Long id, @RequestBody String jsonBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> requestMap = objectMapper.readValue(jsonBody, Map.class);
        String newZoneName = requestMap.get("zoneName");
        if(verifyToken(token)){
            zoneService.editZone(id,newZoneName);
            return ResponseEntity.status(HttpStatus.CREATED).body("Good");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Issue");
    }
    boolean verifyToken(String token){
        return JwtConfig.verifyToken(token);
    }
}
