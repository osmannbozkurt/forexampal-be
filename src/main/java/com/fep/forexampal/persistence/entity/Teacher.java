package com.fep.forexampal.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEACHER")
@SequenceGenerator(name = "SEQ_TEACHER", sequenceName = "SEQ_TEACHER")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(generator = "SEQ_TEACHER", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;
}
