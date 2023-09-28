package com.fep.forexampal.service;

import com.fep.forexampal.dto.UserDto;
import com.fep.forexampal.exception.UserNotFoundException;
import com.fep.forexampal.mapper.UserMapper;
import com.fep.forexampal.persistence.entity.User;
import com.fep.forexampal.persistence.entity.UserClass;
import com.fep.forexampal.persistence.repository.UserClassRepository;
import com.fep.forexampal.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.fep.forexampal.common.enums.ErrorMessage.USER_CLASS_NOT_FOUND;
import static com.fep.forexampal.common.enums.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserClassRepository userClassRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND.getMessage(),
                USER_NOT_FOUND.getCode(), id.toString()));
    }

    public List<User> getUserListByIds(List<Long> ids) {
        return userRepository.findAllByIdIn(ids).orElse(Collections.emptyList());
    }

    public List<UserDto> findByIds(List<Long> ids) {
        List<User> users = getUserListByIds(ids);
        return userMapper.toUserDtoList(users);
    }

    public List<UserDto> search(String query) {
        List<User> users = userRepository.searchAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query, query);
        return userMapper.toUserDtoList(users);
    }
    public UserClass getUserClassByUserId(Long userId) {
        return userClassRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(USER_CLASS_NOT_FOUND.name(), USER_CLASS_NOT_FOUND.getCode()));
    }
}
