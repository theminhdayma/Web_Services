package com.data.session10.controller;

import com.data.session10.model.entity.Notification;
import com.data.session10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long accountId) {
        return ResponseEntity.ok(notificationService.getNotificationsByAccountId(accountId));
    }
}
