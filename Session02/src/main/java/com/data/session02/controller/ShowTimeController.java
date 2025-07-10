package com.data.session02.controller;

import com.data.session02.entity.Schedule;
import com.data.session02.service.MovieService;
import com.data.session02.service.ScheduleService;
import com.data.session02.service.ScreenRoomService;
import com.data.session02.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final ScreenRoomService screenRoomService;
    private final TheaterService theaterService;


    @GetMapping
    public String listShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) Long screenRoomId,
            Model model) {

        List<Schedule> showtimes = scheduleService.filterSchedules(movieId, date, theaterId, screenRoomId);

        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("screenRooms", screenRoomService.getAllScreenRooms());
        model.addAttribute("theaters", theaterService.getAllTheaters());

        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedDate", date);
        model.addAttribute("selectedTheaterId", theaterId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);

        return "showtime/showtime-list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Schedule());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("screenRooms", screenRoomService.getAllScreenRooms());
        return "showtime/showtime-add";
    }


    @PostMapping("/add")
    public String addShowtime(@ModelAttribute("showtime") Schedule showtime) {
        scheduleService.save(showtime);
        return "redirect:/showtimes";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Schedule showtime = scheduleService.findById(id).orElse(null);
        if (showtime == null) {
            return "redirect:/showtimes";
        }
        model.addAttribute("showtime", showtime);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("screenRooms", screenRoomService.getAllScreenRooms());
        return "showtime/showtime-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateShowtime(@PathVariable Long id, @ModelAttribute("showtime") Schedule updated) {
        updated.setId(id);
        scheduleService.update(updated);
        return "redirect:/showtimes";
    }


    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/showtimes";
    }
}
