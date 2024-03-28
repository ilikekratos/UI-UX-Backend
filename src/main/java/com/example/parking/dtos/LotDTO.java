package com.example.parking.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LotDTO extends BaseDTO<Long>{
    private String lot_name;
    private double latitude;
    private double longitude;
    private String token;
}
