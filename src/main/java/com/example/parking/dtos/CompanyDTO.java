package com.example.parking.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyDTO extends BaseDTO<Long>{
    private String company_name;
}
