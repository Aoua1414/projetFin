package com.aoua.medoc.repository;

import com.aoua.medoc.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository <Notification, Long> {
}
