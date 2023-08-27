package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
