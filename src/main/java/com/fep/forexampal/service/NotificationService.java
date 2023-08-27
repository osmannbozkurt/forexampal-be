package com.fep.forexampal.service;

import com.fep.forexampal.dto.NotificationDto;
import com.fep.forexampal.mapper.NotificationMapper;
import com.fep.forexampal.persistence.entity.Notification;
import com.fep.forexampal.persistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NotificationService {


    @Autowired
    private final  NotificationRepository notificationRepository;
    private  final NotificationMapper notificationMapper;

    public List<NotificationDto> getAllByStudent(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);

        return notificationMapper.notificationDtoList(notifications);
    }
}
