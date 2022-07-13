package com.finalproject.secondhand.controller;

import com.finalproject.secondhand.entity.Users;
import com.finalproject.secondhand.service.transaction.NotificationService;
import com.finalproject.secondhand.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Notification", description = "API for access notification")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/notification/")
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
public class NotificationController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "List notification")
    @GetMapping("get")
    public ResponseEntity<?> listNotification(Authentication auth) {
        String username = auth.getName();
        Users users = userService.findByUsername(username);
        return new ResponseEntity<>(notificationService.getNotification(users), HttpStatus.OK);
    }

    @Operation(summary = "is Read")
    @PutMapping("read/{id}")
    public ResponseEntity<?> isRead(Integer id) {
        notificationService.updateIsRead(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
