package com.example.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="lot")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lot extends BaseEntity<Long>{
    @Column(name = "lot_name")
    @NonNull
    private String lot_name;
}