package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<List<User>> findAllByIdIn(List<Long> ids);

    List<User> searchAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
