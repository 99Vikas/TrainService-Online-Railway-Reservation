package com.railway.train.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "tripInfo")
public class Trip {

    @Id
    private String tripId;
    private int baseFare;
    private int durationHrs;
    private int durationMns;
    private String sourceStationCode;
    private String destinationStationCode;
    private String trainNo;


    @Override
    public String toString() {
        return "Trip{" +
                "tripId='" + tripId + '\'' +
                ", baseFare=" + baseFare +
                ", durationHrs=" + durationHrs +
                ", durationMns=" + durationMns +
                ", sourceStationCode='" + sourceStationCode + '\'' +
                ", destinationStationCode='" + destinationStationCode + '\'' +
                ", trainNo='" + trainNo + '\'' +
                '}';
    }




}
