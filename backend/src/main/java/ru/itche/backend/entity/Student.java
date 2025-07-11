package ru.itche.backend.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "photo")
    private String photo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
