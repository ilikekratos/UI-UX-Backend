package com.example.parking.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
@Getter
@Setter
public class LoginResponseDTO {
    String token;
    boolean admin;
}
