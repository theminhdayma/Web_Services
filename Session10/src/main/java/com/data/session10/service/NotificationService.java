package com.data.session10.service;

import com.data.session10.model.entity.Notification;
import com.data.session10.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByAccountId(Long accountId) {
        return notificationRepository.findByAccountId(accountId);
    }
}
