package com.example.parking.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BaseEntity<Long> {
    @Column(name="username",unique = true)
    @NonNull
    private String username;
    @Column(name="password")
    @NonNull
    private String password;
    @Column(name="admin")
    private boolean admin;
}
