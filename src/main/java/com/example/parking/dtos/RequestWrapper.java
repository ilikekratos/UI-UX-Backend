package com.example.parking.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
@Getter
@Setter
public class RequestWrapper <DTO>{
    private DTO dto;
    private TokenDTO tokenDTO;
}
