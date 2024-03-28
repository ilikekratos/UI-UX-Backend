package com.example.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.lang.Nullable;

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
    @Column(name = "occupiedId")
    private Long occupiedId;
    @Column(name = "zoneId")
    private Long zoneId;
}
