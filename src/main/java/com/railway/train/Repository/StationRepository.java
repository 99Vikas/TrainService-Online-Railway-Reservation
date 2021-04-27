package com.railway.train.Repository;

import com.railway.train.Model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends MongoRepository<Station,String> {

    public void deleteByCode(String code);

    public Optional<Station> findByCode(String stationCode);

}
