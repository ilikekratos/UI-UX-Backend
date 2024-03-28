package com.example.parking.dtos;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SpotDTO extends BaseDTO<Long>{
    private boolean occupied;
    private Long zoneId;
}
