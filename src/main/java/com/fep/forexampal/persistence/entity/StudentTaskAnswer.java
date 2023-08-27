package com.fep.forexampal.persistence.entity;

import com.fep.forexampal.common.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "STUDENT_TASK_ANSWER")
@SequenceGenerator(name = "SEQ_STUDENT_TASK_ANSWER", sequenceName = "SEQ_STUDENT_TASK_ANSWER")
@Getter
@Setter
public class StudentTaskAnswer {

    @Id
    @GeneratedValue(generator = "SEQ_STUDENT_TASK_ANSWER", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private StudentTask studentTask;

    @Column(name = "ANSWER_DATE")
    private Date answerDate;

    @Column(name = "COMMENT_ANSWER")
    private String commentAnswer;

    @Column(name = "USER_ID")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "WIDTH")
    private int width;

    @Column(name = "HEIGHT")
    private int height;
}
