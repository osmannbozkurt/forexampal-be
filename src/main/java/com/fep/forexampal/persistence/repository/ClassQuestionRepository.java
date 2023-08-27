package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.ClassQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassQuestionRepository extends JpaRepository<ClassQuestion, Long> {

    Page<ClassQuestion> findAll(Specification<ClassQuestion> specs, Pageable pageable);

}
