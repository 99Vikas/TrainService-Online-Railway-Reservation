package com.railway.train.Controller;

import com.railway.train.ExceptionHanding.APIRequestException;
import com.railway.train.ExceptionHanding.EntityNotFoundException;
import com.railway.train.Model.Train;
import com.railway.train.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        Optional<Train> trainData = trainService.addTrain(train);
        if (trainData.isPresent()){
            return ResponseEntity.ok(trainData.get());
        } else throw new APIRequestException("Bad JSON.");

    }

    @PutMapping("/update/{trainId}")
    public ResponseEntity<Train> updateTrain(@PathVariable String trainId, @RequestBody Train train){
        Optional<Train> trainData = trainService.updateTrain(trainId, train);
        if (trainData.isPresent()){
            return ResponseEntity.ok(trainData.get());
        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @DeleteMapping("/delete/{trainId}")
    public ResponseEntity<Train> deleteTrain(@PathVariable String trainId){
        Optional<Train> trip = trainService.deleteTrain(trainId);
        if (trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @GetMapping("/get-by-id/{trainId}")
    public ResponseEntity<Train> getTrainById(@PathVariable String trainId){
        Optional<Train> train = trainService.getTrainById(trainId);
        if (train.isPresent()){
            return ResponseEntity.ok(train.get());
        } else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @GetMapping("/get/{number}")
    public ResponseEntity<Train> getTrainByNumber(@PathVariable String number){
        Optional<Train> train = trainService.getTrainByNumber(number);
        if (train.isPresent()){
            return ResponseEntity.ok(train.get());
        } else throw new EntityNotFoundException("Train with number " + number + " was not found.");
    }

    @GetMapping("/search/{fromStationCode}/{toStationCode}")
    public List<Train> searchTrains(@PathVariable String fromStationCode, @PathVariable String toStationCode){
        System.out.println(fromStationCode + " " + toStationCode);
        return trainService.searchTrainsByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Train>> getAllStations(){
        Optional<List<Train>> trains = trainService.getAll();
        if (trains.isPresent()){
            return ResponseEntity.ok(trains.get());
        }else throw new EntityNotFoundException("Trains not found.");
    }

}