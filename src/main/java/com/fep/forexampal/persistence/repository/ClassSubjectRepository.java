package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.ClassSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassSubjectRepository extends JpaRepository<ClassSubject, Long> {

    Optional<ClassSubject> findClassSubjectBySubjectId(Long subjectId);
}
