package com.fep.forexampal.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STUDENT")
@SequenceGenerator(name = "SEQ_STUDENT", sequenceName = "SEQ_STUDENT")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(generator = "SEQ_STUDENT", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

}
