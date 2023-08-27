package com.fep.forexampal.controller;
import com.fep.forexampal.dto.NotificationDto;
import com.fep.forexampal.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class  NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(notificationService.getAllByStudent(userId));
    }
}
