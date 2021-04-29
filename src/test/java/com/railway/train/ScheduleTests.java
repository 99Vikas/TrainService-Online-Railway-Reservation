package com.railway.train;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import com.railway.train.Model.Schedule;
import com.railway.train.Repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScheduleTests {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("Test Route Schedule for GetRouteSchedule")
    public void getIdTest() {

        String id="01";

        Optional<Schedule> testSchedule = scheduleRepository.findById(id);

        assertNotNull(testSchedule);
    }

    @Test
    @DisplayName("Test Route Schedule from Route")
    public void getRoute() {

        String id="01";
        Schedule testSchedule = scheduleRepository.findByTripId(id);

        assertNotNull(testSchedule);
    }

}

