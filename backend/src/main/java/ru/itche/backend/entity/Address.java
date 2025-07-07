package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "building", nullable = false)
    private String building;
}

