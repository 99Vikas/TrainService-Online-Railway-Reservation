package com.railway.train.Controller;

import com.railway.train.ExceptionHanding.APIRequestException;
import com.railway.train.ExceptionHanding.EntityNotFoundException;
import com.railway.train.Model.Schedule;
import com.railway.train.Model.Station;
import com.railway.train.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/train/schedules")
public class ScheduleController {

        @Value("${schedule.active}")
        String activeCode;

        @Value(("${schedule.cancelled}"))
        String cancelCode;

        @Autowired
        ScheduleService scheduleService;

        @Autowired
        RestTemplate restTemplate;

        @PostMapping("/add")
        public ResponseEntity<Schedule> createTripSchedule(@RequestBody Schedule schedule) {
                schedule.setStatus(activeCode);
                Optional<Schedule> scheduleData = scheduleService.addSchedule(schedule);
                if (scheduleData.isPresent()) {
                        return ResponseEntity.ok(scheduleData.get());
                } else throw new APIRequestException("Bad JSON");
        }

        @PutMapping("/update/{scheduleId}")
        public ResponseEntity<Schedule> updateTripSchedule(@PathVariable String scheduleId, @RequestBody Schedule schedule) {
                Optional<Schedule> tripScheduleData = scheduleService.updateSchedule(scheduleId, schedule);
                if (tripScheduleData.isPresent()) {
                        return ResponseEntity.ok(tripScheduleData.get());
                } else throw new EntityNotFoundException("Schedule with id " + scheduleId + " was not found.");

        }

        @DeleteMapping("/delete/{scheduleId}")
        public ResponseEntity<Schedule> deleteTripSchedule(@PathVariable String scheduleId) {
                Optional<Schedule> tripSchedule = scheduleService.deleteSchedule(scheduleId);
                if (tripSchedule.isPresent()) {
                        return ResponseEntity.ok(tripSchedule.get());
                } else throw new EntityNotFoundException("Schedule with id " + scheduleId + " was not found.");
        }

        @GetMapping("/get/{scheduleId}")
        public ResponseEntity<Schedule> getTripSchedule(@PathVariable String scheduleId) {
                Optional<Schedule> tripSchedule = scheduleService.getSchedule(scheduleId);
                if (tripSchedule.isPresent()){
                        return ResponseEntity.ok(tripSchedule.get());
                } else throw new EntityNotFoundException("Schedule with id " + scheduleId + " was not found.");
        }

        @GetMapping("/get-trip-by-id/{tripId}/{date}")
        public ResponseEntity<Schedule> getTripScheduleByTripIdAndDate(@PathVariable String tripId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE) LocalDate date) throws ParseException {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                Optional<Schedule> schedule = scheduleService.getTripScheduleByTripIdAndDate(tripId, date);
            //    Optional<Schedule> tripSchedule2 = scheduleService.getTripScheduleByTripIdAndDate2(tripId, date);
                System.out.println(DateTimeFormat.ISO.TIME);
                System.out.println("tripId & Date " + date.toString() + " " + schedule);
                return schedule.map(ResponseEntity::ok).orElse(null);
        }

        @GetMapping("/get-trip-by-id/{tripScheduleId}")
        public ResponseEntity<Schedule> getTripScheduleByTripScheduleId(@PathVariable String tripScheduleId) {
                Optional<Schedule> tripSchedule = scheduleService.getSchedule(tripScheduleId);
                if (tripSchedule.isPresent()){
                        return ResponseEntity.ok(tripSchedule.get());
                }else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
        }

        @PutMapping("/cancel-trip-schedule/{tripScheduleId}")
        public void cancelTripSchedule(@PathVariable String tripScheduleId) {
                Optional<Schedule> tripSchedule = scheduleService.getSchedule(tripScheduleId);
                if (tripSchedule.isPresent()) {
                        tripSchedule.get().setStatus(cancelCode);
                }
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<Schedule>> getAllStations(){
                Optional<List<Schedule>> schedules = scheduleService.getAll();
                if (schedules.isPresent()){
                        return ResponseEntity.ok(schedules.get());
                }else throw new EntityNotFoundException("Schedules not found.");
        }

}
