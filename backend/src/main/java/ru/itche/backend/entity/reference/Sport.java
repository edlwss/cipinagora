package ru.itche.backend.entity.reference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_generator")
    @SequenceGenerator(name = "sport_generator", sequenceName = "sport_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name_sport")
    String name;
}
