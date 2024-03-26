package com.example.parking.converters;

import com.example.parking.dtos.BaseDTO;
import com.example.parking.models.BaseEntity;

import java.io.Serializable;

public interface Convertor <ID extends Serializable,Model extends BaseEntity<ID>,DTO extends BaseDTO<ID>> {
    Model convertDtoToModel(DTO dto);
    DTO convertModelToDto(Model model);
}
