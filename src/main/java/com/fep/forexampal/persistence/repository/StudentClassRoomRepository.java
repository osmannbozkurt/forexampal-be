package com.fep.forexampal.persistence.repository;

import com.fep.forexampal.persistence.entity.StudentClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentClassRoomRepository extends JpaRepository<StudentClassRoom, Long> {

    Optional<List<StudentClassRoom>> findAllByClassRoomId(Long classRoomId);
}
