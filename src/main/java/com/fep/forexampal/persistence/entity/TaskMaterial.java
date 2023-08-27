package com.fep.forexampal.persistence.entity;

import com.fep.forexampal.common.enums.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TASK_MATERIAL")
@SequenceGenerator(name = "SEQ_TASK_MATERIAL", sequenceName = "SEQ_TASK_MATERIAL")
@Getter
@Setter
public class TaskMaterial {

    @Id
    @GeneratedValue(generator = "SEQ_TASK_MATERIAL", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "PATH")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "FILE_TYPE")
    private FileType fileType;

    @Column(name = "WIDTH")
    private int width;

    @Column(name = "HEIGHT")
    private int height;
}
