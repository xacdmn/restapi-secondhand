package com.finalproject.secondhand.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Notification", description = "API for access notification")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/notification/")
@CrossOrigin(origins = {"http://localhost:3000", "https://final-project-21wa2388k-icem87.vercel.app/"}, maxAge = 3600)
public class NotificationController {
}
