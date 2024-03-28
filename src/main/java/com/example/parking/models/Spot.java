package com.example.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="spot")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spot extends BaseEntity<Long>{
    @Column(name = "occupied")
    private boolean occupied;
    @Column(name = "zoneId")
    private Long zoneId;
}
