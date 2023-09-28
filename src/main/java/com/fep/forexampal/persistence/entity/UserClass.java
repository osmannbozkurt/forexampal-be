package com.fep.forexampal.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_CLASS")
@SequenceGenerator(name = "SEQ_USER_CLASS", sequenceName = "SEQ_USER_CLASS")
@Getter
@Setter
public class UserClass {

    @Id
    @GeneratedValue(generator = "SEQ_USER_CLASS", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "CLASS_ROOM_ID")
    private ClassRoom classRoom;
}
