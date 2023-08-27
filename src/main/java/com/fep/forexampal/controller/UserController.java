package com.fep.forexampal.controller;

import com.fep.forexampal.dto.UserDto;
import com.fep.forexampal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(userService.findByIds(ids));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> search(@RequestParam("q") String query) {
        return ResponseEntity.ok(userService.search(query));
    }
}
