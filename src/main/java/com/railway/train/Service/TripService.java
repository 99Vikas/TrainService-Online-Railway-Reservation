package com.railway.train.Service;


import com.railway.train.Model.Trip;
import com.railway.train.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public Optional<Trip> addTrip(Trip trip){
        return Optional.of(tripRepository.save(trip));
    }

    public Optional<Trip> updateTrip(String tripId, Trip trip){
        Optional<Trip> tripData = tripRepository.findByTripId(tripId);
        if(tripData.isPresent()){
            trip.setTripId(tripId);
            return Optional.of(tripRepository.save(trip));
        }
        return Optional.empty();
    }

    public Optional<Trip> deleteTrip(String tripId){
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isPresent()){
            tripRepository.deleteByTripId(tripId);
            return trip;
        }
        return Optional.empty();
    }

    public Optional<Trip> getTrip(String tripId){
        return tripRepository.findByTripId(tripId);
    }

    public List<Trip> getAlTripsBySrcAndDest(String srcStationCode, String destStationCode) {
        return tripRepository.findBySourceStationCodeAndDestinationStationCode(srcStationCode, destStationCode);
    }


    public List<Trip> getAll(){
        return tripRepository.findAll();
    }
}
