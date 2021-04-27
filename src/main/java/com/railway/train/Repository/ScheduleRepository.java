package com.railway.train.Repository;

import com.railway.train.Model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    void deleteByTripId(String tripId);

    Schedule findByTripId(String tipID);

    Optional<Schedule> findById(String tipScheduleID);

    @Query("{$and:[{'tripId': ?0}, {'tripDate': ?1}]}")
    Schedule findByTripIdAndTripDate(String tripId, LocalDate date);

   // Schedule findTripScheduleByTripIdAndTripDate(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)Date date);
}
