package com.fep.forexampal.service;


import com.fep.forexampal.dto.UserDto;
import com.fep.forexampal.mapper.UserMapper;
import com.fep.forexampal.persistence.entity.StudentClassRoom;
import com.fep.forexampal.persistence.entity.User;
import com.fep.forexampal.persistence.repository.StudentClassRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassRoomService {

    private final StudentClassRoomRepository studentClassRoomRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAllByClassRoomId(Long id) {
        List<StudentClassRoom> studentClassRooms = studentClassRoomRepository.findAllByClassRoomId(id).orElse(Collections.emptyList());
        List<User> userList = studentClassRooms.stream().map(StudentClassRoom::getUser).toList();
        return userMapper.toUserDtoList(userList);
    }
}
