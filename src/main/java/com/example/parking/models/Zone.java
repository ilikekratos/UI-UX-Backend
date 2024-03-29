package com.example.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="zone")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Zone extends BaseEntity<Long>{
    @Column(name="lotId")
    @NonNull
    private Long lotId;
    @Column(name="length")
    @NonNull
    @Min(value=1,message = "Length min 1")
    @Max(value=4,message = "Length max 4")
    private Long length;
    @Column(name="width")
    @NonNull
    @Min(value=1,message = "Length min 1")
    @Max(value=4,message = "Length max 4")
    private Long width;
}
