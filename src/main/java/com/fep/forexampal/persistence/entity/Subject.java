package com.fep.forexampal.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SUBJECT")
@SequenceGenerator(name = "SEQ_SUBJECT", sequenceName = "SEQ_SUBJECT")
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(generator = "SEQ_SUBJECT", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private int classLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Subject parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    public List<Subject> getOrderedParents() {
        List<Subject> parents = new ArrayList<>();
        Subject parentSubject = getParent();
        while (Objects.nonNull(parentSubject)) {
            parents.add(0, parentSubject);
            parentSubject = parentSubject.getParent();
        }
        return parents;
    }

    public List<String> getOrderedSubjectNameWithParents() {
        List<Subject> orderedParents = getOrderedParents();
        List<String> subjectNames = new ArrayList<>(orderedParents.stream().map(Subject::getName).toList());
        subjectNames.add(this.getName());
        return subjectNames;
    }

}
