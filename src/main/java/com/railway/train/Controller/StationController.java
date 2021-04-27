package com.railway.train.Controller;

import com.railway.train.ExceptionHanding.APIRequestException;
import com.railway.train.ExceptionHanding.EntityNotFoundException;
import com.railway.train.Model.Station;
import com.railway.train.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/train/stations")
public class StationController {

    @Autowired
    StationService stationService;

    @PostMapping("/add")
    public ResponseEntity<Station> createStation(@RequestBody Station station){
        Optional<Station> stationData = stationService.addStation(station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new APIRequestException("Bad JSON.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable String id, @RequestBody Station station){
        Optional<Station> stationData = stationService.updateStation(id, station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new EntityNotFoundException("Station with id " + id + " was not found.");
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Station> deleteStation(@PathVariable String code){
        Optional<Station> station = stationService.deleteStation(code);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with id " + code + " was not found.");
    }

    @GetMapping("/get/{stationCode}")
    public ResponseEntity<Station> getStation(@PathVariable String stationCode){
        Optional<Station> station = stationService.getStationByCode(stationCode);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with stationCode " + stationCode + " was not found.");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Station>> getAllStations(){
        Optional<List<Station>> trains = stationService.getAll();
        if (trains.isPresent()){
            return ResponseEntity.ok(trains.get());
        }else throw new EntityNotFoundException("Stations not found.");
    }
}