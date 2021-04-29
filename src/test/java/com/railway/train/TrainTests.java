package com.railway.train;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import com.railway.train.Model.Train;
import com.railway.train.Repository.TrainRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TrainTests {

    @Autowired
    private TrainRepository trainRepository;

    @Test
    @DisplayName("Test for Train findByNumber")
    public void findByNumberTrain() {

        String number = "14410";
        Optional<Train> testTrain = trainRepository.findByNumber(number);

        assertNotNull(testTrain);
    }

    @Test
    @DisplayName("Test for Train getTrainById")
    public void findByTrain() {

        String id = "607b353ae5ed7036efccf9fc";
        Optional<Train> testTrain = trainRepository.findById(id);

        assertNotNull(testTrain);
    }
}

