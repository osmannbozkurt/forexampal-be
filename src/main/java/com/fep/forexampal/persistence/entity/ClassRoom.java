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
@Table(name = "CLASSROOM")
@SequenceGenerator(name = "SEQ_CLASSROOM", sequenceName = "SEQ_CLASSROOM")
@Getter
@Setter
public class ClassRoom {

    @Id
    @GeneratedValue(generator = "SEQ_CLASSROOM", strategy = GenerationType.SEQUENCE)
    private Long id;

    private int level;

    private String section;
}
