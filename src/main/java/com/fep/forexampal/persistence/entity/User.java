package com.fep.forexampal.persistence.entity;

import com.fep.forexampal.common.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(generator = "SEQ_USER", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;
}
