package com.railway.train.Repository;

import com.railway.train.Model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends MongoRepository<Train, String> {

    public Optional<Train> findByNumber(String number);

    public void deleteById(String number);

    public List<Train> findTrainByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode);
}
