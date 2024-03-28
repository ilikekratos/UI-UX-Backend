package com.example.parking.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;

}
