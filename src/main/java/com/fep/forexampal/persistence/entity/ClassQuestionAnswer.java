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
import jakarta.persistence.ManyToOne;
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
@Table(name = "CLASS_QUESTION_ANSWER")
@SequenceGenerator(name = "SEQ_CLASS_QUESTION_ANSWER", sequenceName = "SEQ_CLASS_QUESTION_ANSWER")
@Getter
@Setter
public class ClassQuestionAnswer {

    @Id
    @GeneratedValue(generator = "SEQ_CLASS_QUESTION_ANSWER", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private ClassQuestion classQuestion;

    @OneToOne
    private User user;

    @Column(name = "IS_CORRECT")
    private boolean correct = false;

    private String description;

    @Embedded
    private Image image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate = new Date();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLASS_QUESTION_ANSWER_TAGGED_USER",
            joinColumns = @JoinColumn(name = "CLASS_QUESTION_ANSWER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    private Set<User> taggedUsers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private ClassQuestionAnswer parent;

}
