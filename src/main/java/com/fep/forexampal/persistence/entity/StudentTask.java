package com.fep.forexampal.persistence.entity;

import com.fep.forexampal.common.enums.StudentTaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "STUDENT_TASK")
@SequenceGenerator(name = "SEQ_STUDENT_TASK", sequenceName = "SEQ_STUDENT_TASK")
@Getter
@Setter
public class StudentTask {

    @Id
    @GeneratedValue(generator = "SEQ_STUDENT_TASK", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    private Task task;

    @Enumerated(EnumType.STRING)
    private StudentTaskStatus status;

    @Column(name = "COR_ANS_COUNT")
    private int correctAnswerCount;

    @Column(name = "WRONG_ANS_COUNT")
    private int wrongAnswerCount;

    @Column(name = "GRADE")
    private BigDecimal grade;

    @OneToOne
    private Teacher teacher;
}
