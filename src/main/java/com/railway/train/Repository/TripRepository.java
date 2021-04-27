package com.railway.train.Repository;

import com.railway.train.Model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends MongoRepository<Trip,String> {

    public Optional<Trip> findByTripId(String tripId);
    public void deleteByTripId(String tripId);
    public List<Trip> findBySourceStationCodeAndDestinationStationCode(String srcStationCode, String destStationCode);
}
