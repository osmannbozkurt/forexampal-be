package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.StudentTask;
import com.fep.forexampal.persistence.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByParentIsNull();
    List<Subject> findAllByParentId(long id);
}


