package com.lalit.clients.notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;



// @FeignClient("notification")
@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {
    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}