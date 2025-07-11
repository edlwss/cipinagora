package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sport_center")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SportsCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "center_generator")
    @SequenceGenerator(name = "center_generator", sequenceName = "sport_center_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    private Address address;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

