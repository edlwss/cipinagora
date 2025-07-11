package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
