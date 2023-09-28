package com.fep.forexampal.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLASS_QUESTION")
@SequenceGenerator(name = "SEQ_CLASS_QUESTION", sequenceName = "SEQ_CLASS_QUESTION")
@Getter
@Setter
public class ClassQuestion {

    @Id
    @GeneratedValue(generator = "SEQ_CLASS_QUESTION", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private int reward;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_SUBJECT_ID")
    private ClassSubject classSubject;

    @Column(name = "CLASS_LEVEL")
    private int classLevel;

    @Embedded
    private Image image = new Image();

    @Column(name = "CORRECT_ANSWER")
    private String correctAnswer;

    private boolean solved = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate = new Date();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classQuestion", fetch = FetchType.LAZY)
    private Set<ClassQuestionAnswer> classQuestionAnswer = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLASS_QUESTION_TAGGED_USER",
            joinColumns = @JoinColumn(name = "CLASS_QUESTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    private Set<User> taggedUser = new HashSet<>();

}
