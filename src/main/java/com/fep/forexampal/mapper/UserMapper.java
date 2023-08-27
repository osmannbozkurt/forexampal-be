package com.fep.forexampal.mapper;

import com.fep.forexampal.dto.UserDto;
import com.fep.forexampal.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface UserMapper {

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);
}
