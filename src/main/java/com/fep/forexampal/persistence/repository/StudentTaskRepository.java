package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.StudentTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTaskRepository extends JpaRepository<StudentTask, Long> {

    List<StudentTask> findAllByStudentId(Long studentId);
}
