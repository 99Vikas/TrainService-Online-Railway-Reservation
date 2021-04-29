package com.railway.train;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.railway.train.Model.Station;
import com.railway.train.Repository.StationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StationTests {

    @Autowired
    private StationRepository stationRepository;


    @Test
    @DisplayName("Test for Station getStationByCode")
    public void findByCodeStation() {

        String code = "DLI";
        Optional<Station> testStation = stationRepository.findByCode(code);

        assertNotNull(testStation);

    }

    @Test
    @DisplayName("Test for Station getStationById")
    public void findByIdStation() {

        String id = "607e62367677ad4582ed32dd";

        Optional<Station> testStation = stationRepository.findById(id);

        assertNotNull(testStation);
    }
}

