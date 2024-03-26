package com.example.parking.dtos;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZoneDTO extends BaseDTO<Long>{
    private Long lot_id;
    private Long length;
    private Long width;
}
