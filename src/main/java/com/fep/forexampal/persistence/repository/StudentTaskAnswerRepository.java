package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.StudentTaskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTaskAnswerRepository extends JpaRepository<StudentTaskAnswer, Long> {

    List<StudentTaskAnswer> findAllByStudentTaskIdOrderByAnswerDateAsc(Long studentTaskId);
}
