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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpotController {
    private SpotRepository spotRepository;
    private SpotConvertor spotConvertor;
    @Autowired
    private SpotService spotService;
    @GetMapping(value = "spot/getAllByZone")
    public ResponseEntity<List<SpotDTO>> getAllSpots(@RequestBody RequestWrapper<Long> requestWrapper)
    {
        try{
            if(verifyToken(requestWrapper.getTokenDTO().getToken())){
                List<SpotDTO> temp = spotService.getAllByZone(requestWrapper.getDto()).stream().map(lot -> spotConvertor.convertModelToDto(lot)).toList();
                return ResponseEntity.status(HttpStatus.OK).body(temp);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
    @PostMapping(value = "spot/add")
    public ResponseEntity<String> addSpot(@RequestBody RequestWrapper<SpotDTO> requestWrapper){
        if(verifyToken(requestWrapper.getTokenDTO().getToken())){
            var spot= spotConvertor.convertDtoToModel(requestWrapper.getDto());
            if(spotService.addSpot(spot.getZoneId())){
                return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PutMapping(value = "spot/update")
    public ResponseEntity<String> updateSpot(@RequestBody RequestWrapper<SpotDTO> requestWrapper){
        if(verifyToken(requestWrapper.getTokenDTO().getToken())){
            var spot = spotConvertor.convertDtoToModel(requestWrapper.getDto());
            if(spotService.updateSpot(spot.getOccupiedId(),spot.getId())){
                return ResponseEntity.status(HttpStatus.OK).body("Successful");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @DeleteMapping(value = "spot/delete")
    public ResponseEntity<String> deleteSpot(@RequestBody RequestWrapper<Long> requestWrapper) throws NotFoundException {

        if(verifyToken(requestWrapper.getTokenDTO().getToken())){
            try{
                if(spotService.deleteSpot(requestWrapper.getDto())){
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
