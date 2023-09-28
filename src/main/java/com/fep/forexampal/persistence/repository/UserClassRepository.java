package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClassRepository extends JpaRepository<UserClass, Long> {

    Optional<UserClass> findByUserId(Long id);
}
