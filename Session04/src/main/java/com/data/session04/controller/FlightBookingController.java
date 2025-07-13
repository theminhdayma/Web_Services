package com.data.session04.controller;

import com.data.session04.entity.Booking;
import com.data.session04.entity.Flight;
import com.data.session04.service.BookingService;
import com.data.session04.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class FlightBookingController {

    private final FlightService flightService;

    private final BookingService bookingService;

    @GetMapping
    public String viewFlights(Model model,
                              @RequestParam(defaultValue = "") String from,
                              @RequestParam(defaultValue = "") String to,
                              @RequestParam(defaultValue = "0") int page) {
        Page<Flight> flightPage = flightService.searchFlights(from, to, page, 5);
        model.addAttribute("flightPage", flightPage);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "flight/flightList";
    }

    @PostMapping("/book/{flightId}")
    public String book(@PathVariable Integer flightId,
                       @RequestParam String customerName,
                       @RequestParam String customerPhone) {
        Flight flight = flightService.findById(flightId);
        if (flight != null) {
            bookingService.bookFlight(flight, customerName, customerPhone);
        }
        return "redirect:/";
    }

    @GetMapping("/bookings")
    public String viewBookings(@RequestParam String phone, Model model) {
        List<Booking> bookings = bookingService.getBookingsByPhone(phone);
        model.addAttribute("bookings", bookings);
        model.addAttribute("phone", phone);
        return "booking/bookingList";
    }

    @PostMapping("/cancel/{id}")
    public String cancel(@PathVariable Integer id, @RequestParam String phone) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings?phone=" + phone;
    }
}
