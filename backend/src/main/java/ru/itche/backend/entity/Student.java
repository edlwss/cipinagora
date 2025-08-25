package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.enums.Gender;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @SequenceGenerator(name = "student_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private FullName fullName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "age_id", nullable = false)
    private AgeCategories age;

    @Column(name = "photo")
    private String photo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
