package com.fep.forexampal.persistence.entity;

import com.fep.forexampal.common.enums.TaskStatus;
import com.fep.forexampal.common.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TASK")
@SequenceGenerator(name = "SEQ_TASK", sequenceName = "SEQ_TASK")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(generator = "SEQ_TASK", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_TYPE")
    private TaskType taskType;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DAY")
    private Date startDay;

    @Column(name = "START_HOUR")
    private String startHour;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DAY")
    private Date endDay;

    @Column(name = "END_HOUR")
    private String endHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Column(name = "REWARD")
    private int reward;

    @Column(name = "PUBLIC")
    private boolean isPublic;

    @Column(name = "CREATOR_ID")
    private Long creatorId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private Set<TaskMaterial> taskMaterials;
}
