package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.SpotConvertor;
import com.example.parking.dtos.LotDTO;
import com.example.parking.dtos.RequestWrapper;
import com.example.parking.dtos.SpotDTO;
import com.example.parking.dtos.ZoneDTO;
import com.example.parking.exceptions.NotFoundException;
import com.example.parking.repository.SpotRepository;
import com.example.parking.service.SpotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SpotController {
    private SpotRepository spotRepository;
    @Autowired
    private SpotConvertor spotConvertor;
    @Autowired
    private SpotService spotService;
    @GetMapping(value = "spot/getAllByZone/{id}")
    public ResponseEntity<List<SpotDTO>> getAllSpots(@RequestHeader("Authorization") String token, @PathVariable("id") Long zoneId)
    {
        try{
            if(verifyToken(token)){
                List<SpotDTO> temp = spotService.getAllByZone(zoneId).stream().map(lot -> spotConvertor.convertModelToDto(lot)).toList();
                return ResponseEntity.status(HttpStatus.OK).body(temp);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
    @PostMapping(value = "spot/add")
    public ResponseEntity<String> addSpot(@RequestHeader("Authorization") String token,@RequestBody String jsonBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Any, String> requestMap = objectMapper.readValue(jsonBody, Map.class);
        String zoneId = requestMap.get("zoneId");
        if(verifyToken(token)){
            if(spotService.addSpot(Long.valueOf(zoneId))){
                return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PutMapping(value = "spot/clear/{id}")
    public ResponseEntity<String> updateSpot(@RequestHeader("Authorization") String token,@PathVariable Long id){
        if(verifyToken(token)){
            if(spotService.clearSpot(id)){
                return ResponseEntity.status(HttpStatus.OK).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PutMapping(value = "spot/update/{id}")
    public ResponseEntity<String> updateSpot(@RequestHeader("Authorization") String token,@PathVariable Long id,@RequestBody String jsonBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> requestMap = objectMapper.readValue(jsonBody, Map.class);
        String username = requestMap.get("username");
        if(verifyToken(token)){
            spotService.clearSpotByUser(username);
            if(spotService.updateSpot(username,id)){
                return ResponseEntity.status(HttpStatus.OK).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @GetMapping(value = "spot/check/{username}")
    public ResponseEntity<String> checkSpot(@RequestHeader("Authorization") String token,@PathVariable String username) throws JsonProcessingException {
        if(verifyToken(token)){
            if(spotService.existSpotOccupied(username)){
                return ResponseEntity.status(HttpStatus.OK).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @DeleteMapping(value = "spot/delete/{id}")
    public ResponseEntity<String> deleteSpot(@RequestHeader("Authorization") String token,@PathVariable Long id) throws NotFoundException {

        if(verifyToken(token)){
            try{
                if(spotService.deleteSpot(id)){
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
