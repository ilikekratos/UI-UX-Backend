package com.example.parking.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class BaseEntity<ID extends Serializable>implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

}
