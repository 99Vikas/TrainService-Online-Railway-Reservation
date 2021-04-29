package com.railway.train;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import com.railway.train.Model.Trip;
import com.railway.train.Repository.TripRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TripTests {

    @Autowired
    private TripRepository tripRepository;

    @Test
    @DisplayName("Test for Route TripID")
    public void findByIdTest() {

        String id = "6089309c67fd2267044dc529";

        Optional<Trip> testTrip = tripRepository.findById(id);

        assertNotNull(testTrip);
    }

}

