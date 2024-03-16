package com.example.parking.dtos;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class UserDTO extends BaseDTO<Long>{
    private String username;
    private String password;
    private boolean admin;
    private Long company_id;
}
