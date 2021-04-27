package com.railway.train.Service;

import com.railway.train.Model.Schedule;
import com.railway.train.Model.Station;
import com.railway.train.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public Optional<Station> addStation(Station station) {
        return Optional.of(stationRepository.save(station));
    }

    public Optional<Station> updateStation(String id, Station station) {
        Optional<Station> stationData = stationRepository.findById(id);
        if (stationData.isPresent()) {
            station.setId(id);
            return Optional.of(stationRepository.save(station));
        }
        return Optional.empty();
    }


    public Optional<Station> deleteStation(String code) {
        Optional<Station> station = stationRepository.findByCode(code);
        if (station.isPresent()) {
            stationRepository.deleteByCode(code);
            return station;
        }
        return Optional.empty();
    }

    public Optional<Station> getStationByCode(String stationCode) {
        Optional<Station> station = stationRepository.findByCode(stationCode);
        return station;
    }

    public Optional<List<Station>> getAll() {
        return Optional.ofNullable(stationRepository.findAll());
    }

}
