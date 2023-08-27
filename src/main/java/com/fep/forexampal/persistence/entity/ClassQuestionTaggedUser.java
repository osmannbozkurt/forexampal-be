package com.fep.forexampal.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CLASS_QUESTION_TAGGED_USER")
@SequenceGenerator(name = "SEQ_CLASS_QUESTION_TAGGED_USER", sequenceName = "SEQ_CLASS_QUESTION_TAGGED_USER")
@Getter
@Setter
public class ClassQuestionTaggedUser {

    @Id
    @GeneratedValue(generator = "SEQ_CLASS_QUESTION_TAGGED_USER", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_QUESTION_ID")
    private ClassQuestion classQuestion;

    @OneToOne
    private User user;
}
