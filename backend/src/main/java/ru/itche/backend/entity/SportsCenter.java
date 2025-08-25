package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.entity.valueobject.Address;
import ru.itche.backend.entity.reference.Sport;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "center_types_sports",
            joinColumns = @JoinColumn(name = "center_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private Set<Sport> sports = new HashSet<>();

}

