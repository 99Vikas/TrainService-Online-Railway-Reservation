package com.railway.train.Service;

import com.railway.train.Model.Schedule;
import com.railway.train.Model.Station;
import com.railway.train.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Optional<Schedule> addSchedule(Schedule schedule){
        return Optional.of(scheduleRepository.save(schedule));
    }

    public Optional<Schedule> updateSchedule(String scheduleID, Schedule schedule) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(scheduleID);
        if(scheduleData.isPresent()){
            System.out.println("service update " + schedule);
            schedule.setId(scheduleID);
            return Optional.of(scheduleRepository.save(schedule));
        }
        return Optional.empty();
    }

    public Optional<Schedule> deleteSchedule(String scheduleId){
        Optional<Schedule> tripSchedule = scheduleRepository.findById(scheduleId);
        if (tripSchedule.isPresent()){
            scheduleRepository.deleteByTripId(scheduleId);
            return tripSchedule;
        }
        return Optional.empty();
    }

    public Optional<Schedule> getSchedule(String scheduleId){
        return scheduleRepository.findById(scheduleId);
    }

    public Optional<Schedule> getScheduleByTripIdAndDate(String tripId, LocalDate date){
        return Optional.ofNullable(scheduleRepository.findByTripIdAndTripDate(tripId, date));
    }

    public Optional<List<Schedule>> getAll() {
        return Optional.ofNullable(scheduleRepository.findAll());
    }

    //public Schedule getScheduleByTripIdAndDate2(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
    //    return scheduleRepository.findByTripIdAndTripDate(tripId, date);
   // }
}

