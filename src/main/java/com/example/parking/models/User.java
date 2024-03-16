package com.example.parking.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseEntity<Long> {
    @Column(name="username")
    @NonNull
    private String username;
    @Column(name="password")
    @NonNull
    private String password;
    @Column(name="admin")
    private boolean admin;
    @Column(name="company_id")
    @NonNull
    private Long company_id;
}
