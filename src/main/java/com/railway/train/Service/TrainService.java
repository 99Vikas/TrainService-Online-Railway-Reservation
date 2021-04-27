package com.railway.train.Service;

import com.railway.train.Model.Train;
import com.railway.train.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    public Optional<Train> addTrain(Train train){
        return Optional.of(trainRepository.save(train));
    }

    public Optional<Train> updateTrain(String id, Train train){
        Optional<Train> trainData = trainRepository.findById(id);
        if(trainData.isPresent()){
            train.setId(id);
            return Optional.of(trainRepository.save(train));
        }
        return Optional.empty();
    }

    public Optional<Train> deleteTrain(String trainId){
        Optional<Train> train = trainRepository.findById(trainId);
        if(train.isPresent()){
            trainRepository.deleteById(trainId);
            return train;
        }
        return Optional.empty();
    }

    public Optional<Train> getTrainByNumber(String number){
        return trainRepository.findByNumber(number);
    }

    public Optional<Train> getTrainById(String trainId){
        return trainRepository.findById(trainId);
    }

    public List<Train> searchTrainsByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode) {
        return trainRepository.findTrainByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }
    public Optional<List<Train>> getAll() {
        return Optional.of(trainRepository.findAll());
    }
}

