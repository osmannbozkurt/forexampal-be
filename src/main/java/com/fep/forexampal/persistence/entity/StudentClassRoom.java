package com.fep.forexampal.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "STUDENT_CLASSROOM")
@SequenceGenerator(name = "SEQ_STUDENT_CLASSROOM", sequenceName = "SEQ_STUDENT_CLASSROOM")
@Getter
@Setter
public class StudentClassRoom {

    @Id
    @GeneratedValue(generator = "SEQ_STUDENT_CLASSROOM", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private ClassRoom classRoom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
