package com.lalit.clients.notification;

public record NotificationRequest(Integer toCustomerId,
                                  String toCustomerName,
                                  String message
) {
}