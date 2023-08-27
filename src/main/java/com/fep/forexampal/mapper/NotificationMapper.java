package com.fep.forexampal.mapper;


import com.fep.forexampal.dto.NotificationDto;
import com.fep.forexampal.dto.StudentTaskDto;
import com.fep.forexampal.persistence.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface NotificationMapper {


    @Mapping(target = "type", source = "detailType")
    NotificationDto notificationDto(Notification notification);

    List<NotificationDto> notificationDtoList(List<Notification> notifications);
}

