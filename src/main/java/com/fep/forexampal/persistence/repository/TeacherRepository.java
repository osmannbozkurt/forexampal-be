package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
