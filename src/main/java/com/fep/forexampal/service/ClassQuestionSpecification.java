package com.fep.forexampal.service;

import com.fep.forexampal.common.enums.ClassQuestionFilterSolvedStatus;
import com.fep.forexampal.common.utils.DateUtils;
import com.fep.forexampal.dto.ClassFilterOptions;
import com.fep.forexampal.persistence.entity.ClassQuestion;
import com.fep.forexampal.persistence.entity.Subject;
import com.fep.forexampal.persistence.repository.SubjectRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ClassQuestionSpecification {

    private final SubjectRepository subjectRepository;

    private static final String SUBJECT_PROP = "classSubject";

    public Specification<ClassQuestion> generateFilterSpecs(ClassFilterOptions options) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (options.isRewarded()) {
                predicates.add(criteriaBuilder.greaterThan(root.get("reward"), 0));
            }

            if (Objects.nonNull(options.getChildSubjectId())) {
                predicates.add(criteriaBuilder.equal(root.join(SUBJECT_PROP).get("id"), options.getChildSubjectId()));
            } else if (Objects.nonNull(options.getSubjectId())) {
                List<Subject> subject = subjectRepository.findAllByParentId(options.getSubjectId());
                List<Long> subjectList = subject.stream().map(Subject::getId).toList();
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.join(SUBJECT_PROP).get("id"));
                subjectList.forEach(in::value);
                 predicates.add(in);
            }

            if (options.getQuestionStatus() == ClassQuestionFilterSolvedStatus.SOLVED) {
                predicates.add(criteriaBuilder.equal(root.get("solved"), true));
            } else if (options.getQuestionStatus() == ClassQuestionFilterSolvedStatus.UNSOLVED) {
                predicates.add(criteriaBuilder.equal(root.get("solved"), false));
            }

            if (Objects.nonNull(options.getDate())) {
                Date date = DateUtils.getDateWithoutTime(options.getDate());
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), date));
            }

            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
