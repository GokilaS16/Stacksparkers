package com.cogig.service;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    public void sendBookingNotification(
            String customerName,
            String customerMobile,
            String service) {

        System.out.println(
                "WhatsApp notification ready for: "
                + customerName
                + " - "
                + service
                + " - "
                + customerMobile
        );
    }

    public void sendBookingStatusNotification(
            String customerName,
            String customerMobile,
            String service,
            String status) {

        System.out.println(
                "WhatsApp status notification ready for: "
                + customerName
                + " - "
                + service
                + " - Status: "
                + status
                + " - "
                + customerMobile
        );
    }
}