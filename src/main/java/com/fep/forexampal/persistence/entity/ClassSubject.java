package com.fep.forexampal.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CLASS_SUBJECT")
@SequenceGenerator(name = "SEQ_CLASS_SUBJECT", sequenceName = "SEQ_CLASS_SUBJECT")
@Getter
@Setter
public class ClassSubject {

    @Id
    @GeneratedValue(generator = "SEQ_CLASS_SUBJECT", strategy = GenerationType.SEQUENCE)
    private Long id;

/*    @OneToMany(mappedBy = "classSubject", fetch = FetchType.LAZY)
    private Set<ClassRoom> classRoom = new HashSet<>();*/

    @OneToOne
    private Subject subject;

    public List<String> getOrderedSubjectNameWithParents() {
        return getSubject().getOrderedSubjectNameWithParents();
    }
}
