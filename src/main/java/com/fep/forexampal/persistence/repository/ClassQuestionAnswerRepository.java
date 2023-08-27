package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.ClassQuestionAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClassQuestionAnswerRepository extends JpaRepository<ClassQuestionAnswer, Long>, PagingAndSortingRepository<ClassQuestionAnswer, Long> {

    List<ClassQuestionAnswer> findAllByClassQuestionIdOrderByCreatedDateDesc(Long classQuestionId);

    Page<ClassQuestionAnswer> findAllByClassQuestionIdAndParentIdIsNullOrderByCreatedDateAsc(Long classQuestionId, Pageable pageable);

    List<ClassQuestionAnswer> findAllByParentIdOrderByCreatedDateAsc(Long parentId);
}
