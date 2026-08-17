package com.kritsn.notificationservice.presentation.rest.controller;

import com.kritsn.notificationservice.presentation.rest.dto.SendNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Stands in for a real email/SMS provider — logs instead of sending, and
 * has no persistence: it's purely the place a future Kafka consumer
 * (order/payment status events) will hang its notification logic off.
 */
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestBody SendNotificationRequest request) {
        log.info("Notification to {}: {}", request.recipient(), request.message());
    }
}
