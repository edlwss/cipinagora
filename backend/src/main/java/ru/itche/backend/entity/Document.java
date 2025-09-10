package ru.itche.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_generator")
    @SequenceGenerator(name = "doc_generator", sequenceName = "doc_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "instructor_id", nullable = false)
    @ManyToOne
    private Instructor instructor;

    @Column(name = "doc", nullable = false)
    private String doc;
}
