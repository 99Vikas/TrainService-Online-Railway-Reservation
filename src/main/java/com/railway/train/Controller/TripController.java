package com.railway.train.Controller;

import com.railway.train.ExceptionHanding.APIRequestException;
import com.railway.train.ExceptionHanding.EntityNotFoundException;
import com.railway.train.Model.Trip;
import com.railway.train.Model.TripSearchRequest;
import com.railway.train.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/train/trips")
public class TripController {

    @Autowired
    TripService tripService;

    @Value("${FirstAC.fare}")
    int fare1AC;

    @Value("${SecondAC.fare}")
    int fare2AC;

    @Value("${ThirdAC.fare}")
    int fare3AC;

    @Value("${FirstClass.fare}")
    int fareFirstClass;

    @Value("${ChairCar.fare}")
    int fareCC;

    @Value("${Sleeper.fare}")
    int fareSleeper;

    @Value("${codes.first-ac}")
    private String firstAcCode;

    @Value("${codes.second-ac}")
    private String secondAcCode;

    @Value(("${codes.third-ac}"))
    private String thirdAcCode;

    @Value("${codes.first-class}")
    private String firstClassCode;

    @Value("${codes.chair-car}")
    private String chairCarCode;

    @Value("${codes.sleeper}")
    private String sleeperCode;


    @PostMapping("/add")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip){
        Optional<Trip> tripData = tripService.addTrip(trip);
        if (tripData.isPresent()) {
            return ResponseEntity.ok(tripData.get());
        } else throw new APIRequestException("Bad Json");
    }

    @PutMapping("/update/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable String tripId, @RequestBody Trip trip){
        Optional<Trip> tripData = tripService.updateTrip(tripId, trip);
        if (tripData.isPresent()){
            return ResponseEntity.ok(tripData.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable String tripId){
        Optional<Trip> trip = tripService.deleteTrip(tripId);
        if (trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @GetMapping("/get/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable String tripId){
        System.out.println(tripId);
        Optional<Trip> trip = tripService.getTrip(tripId);
        if(trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @GetMapping("/get-trip-by-src-dest")
    public List<Trip> getTripsBySrcAndDest(@RequestBody TripSearchRequest tripSearchRequestBody){
        return tripService.getAllTripsBySrcAndDest(tripSearchRequestBody.getSrcStationCode(), tripSearchRequestBody.getDestStationCode());
    }

    @GetMapping("getFare/{tripId}/{distance}/{classes}/{qty}")
    public ResponseEntity<Integer> getFare(@PathVariable int distance, @PathVariable String classes, @PathVariable int qty,
                                           @PathVariable String tripId){

        Integer TotalFare=0;
        Optional<Trip> trip = tripService.getTrip(tripId);
        if(trip.isPresent()) {
            if (classes.equals(firstAcCode)) {
                TotalFare = trip.get().getBaseFare() + ((fare1AC * distance) * qty);
            } else if (classes.equals(secondAcCode)) {
                TotalFare = trip.get().getBaseFare() + ((fare2AC * distance) * qty);
            } else if (classes.equals(thirdAcCode)) {
                TotalFare = trip.get().getBaseFare() + ((fare3AC * distance) * qty);
            } else if (classes.equals(firstClassCode)) {
                TotalFare = trip.get().getBaseFare() + ((fareFirstClass * distance) * qty);
            } else if (classes.equals(chairCarCode)) {
                TotalFare = trip.get().getBaseFare() + ((fareCC * distance) * qty);
            } else {
                TotalFare = trip.get().getBaseFare() + ((fareSleeper * distance) * qty);
            }
        }
        return ResponseEntity.ok(TotalFare);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Trip>> getAllTrips(){
        Optional<List<Trip>> trips = Optional.ofNullable(tripService.getAll());
        if (trips.isPresent()){
            return ResponseEntity.ok(trips.get());
        }else throw new EntityNotFoundException("Trips not found.");
    }
}
