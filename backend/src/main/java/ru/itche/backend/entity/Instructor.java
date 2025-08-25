package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.enums.Gender;
import ru.itche.backend.entity.reference.Sport;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_generator")
    @SequenceGenerator(name = "instructor_generator", sequenceName = "instructor_id_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private FullName fullName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "age_id", nullable = false)
    private AgeCategories age;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "skill_description", nullable = false)
    private String skillDescription;

    @Column(name = "certificate_number", nullable = false)
    private String certificateNumber;

    @Column(name = "data_verified", nullable = false)
    private boolean dataVerified;

    @Column(name = "official_employment", nullable = false)
    private boolean officialEmployment;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "instructor_sports",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private Set<Sport> sports = new HashSet<>();

}
