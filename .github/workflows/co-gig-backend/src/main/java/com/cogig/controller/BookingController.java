package com.cogig.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogig.model.Booking;
import com.cogig.model.Notification;
import com.cogig.repository.BookingRepository;
import com.cogig.repository.NotificationRepository;
import com.cogig.service.WhatsAppService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingRepository repository;
    private final NotificationRepository notificationRepository;
    private final WhatsAppService whatsAppService;

    public BookingController(
            BookingRepository repository,
            NotificationRepository notificationRepository,
            WhatsAppService whatsAppService) {

        this.repository = repository;
        this.notificationRepository = notificationRepository;
        this.whatsAppService = whatsAppService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody Booking booking) {

        booking.setStatus("PENDING");

        Booking savedBooking = repository.save(booking);

        Notification notification = new Notification();
        notification.setUserId(savedBooking.getCustomerId());
        notification.setMessage("Your booking has been created");
        notification.setRead(false);

        notificationRepository.save(notification);

        // WhatsApp notification
        whatsAppService.sendBookingNotification(
                savedBooking.getCustomerName(),
                savedBooking.getCustomerMobile(),
                savedBooking.getService()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBooking);
    }

    @GetMapping
    public List<Booking> getBookings(
            @RequestParam(required = false) String customerName) {

        if (customerName != null && !customerName.isBlank()) {
            return repository.findByCustomerNameIgnoreCase(customerName);
        }

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PatchMapping("/{id}/status")
    public Booking updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Booking booking = repository.findById(id).orElseThrow();

        String newStatus = status.toUpperCase();

        if (!newStatus.equals("PENDING")
                && !newStatus.equals("ACCEPTED")
                && !newStatus.equals("REJECTED")
                && !newStatus.equals("COMPLETED")) {

            throw new IllegalArgumentException(
                    "Invalid status. Use PENDING, ACCEPTED, REJECTED or COMPLETED");
        }

        booking.setStatus(newStatus);

        Booking savedBooking = repository.save(booking);

        // WhatsApp status notification
        whatsAppService.sendBookingStatusNotification(
                savedBooking.getCustomerName(),
                savedBooking.getCustomerMobile(),
                savedBooking.getService(),
                newStatus
        );

        if (newStatus.equals("ACCEPTED")) {
            Notification notification = new Notification();
            notification.setUserId(savedBooking.getCustomerId());
            notification.setMessage("Your booking has been accepted");
            notification.setRead(false);
            notificationRepository.save(notification);
        }

        if (newStatus.equals("REJECTED")) {
            Notification notification = new Notification();
            notification.setUserId(savedBooking.getCustomerId());
            notification.setMessage("Your booking has been rejected");
            notification.setRead(false);
            notificationRepository.save(notification);
        }

        if (newStatus.equals("COMPLETED")) {
            Notification notification = new Notification();
            notification.setUserId(savedBooking.getCustomerId());
            notification.setMessage("Your booking has been completed");
            notification.setRead(false);
            notificationRepository.save(notification);
        }

        return savedBooking;
    }
}