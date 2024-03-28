package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.LotConvertor;
import com.example.parking.dtos.LotDTO;
import com.example.parking.dtos.RequestWrapper;
import com.example.parking.service.LotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotController {
    public static final Logger logger = LoggerFactory.getLogger(LotController.class);
    private final JwtConfig jwtConfig;
    @Autowired
    private LotService lotService;
    @Autowired
    LotConvertor lotConverter;
    @Autowired
    public LotController(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    @GetMapping(value = "lot/getAll")
    public ResponseEntity<List<LotDTO>> getAllLots(){
        List<LotDTO> temp = lotService.getAll().stream().map(lot -> lotConverter.convertModelToDto(lot)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(temp);
    }
    @PostMapping(value = "lot/add")
    public ResponseEntity<String> addLot(@RequestBody RequestWrapper<LotDTO> requestWrapper){
        if(verifyToken(requestWrapper.getTokenDTO().getToken())){
        var lot= lotConverter.convertDtoToModel(requestWrapper.getDto());
        if(lotService.checkLot_name(lot.getLotName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lot Exists");
        }
        lotService.addLot(lot.getLotName(),lot.getLatitude(),lot.getLongitude());
        return ResponseEntity.status(HttpStatus.CREATED).body("Good");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Token Issue");
    }
    boolean verifyToken(String token){
        return JwtConfig.verifyToken(token);
    }
}
