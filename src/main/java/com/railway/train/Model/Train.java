package com.railway.train.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection ="trainInfo")
public class Train{

    @Id
    private String id;
    @Indexed
    private String number;
    private String name;
    private String fromStationCode;
    private String toStationCode;
    private int firstAcSeats;
    private int secondAcSeats;
    private int thirdAcSeats;
    private int firstClassSeats;
    private int chairCarSeats;
    private int sleeperSeats;
    private int durationHrs;
    private int durationMns;
    private LocalTime departure;
    private LocalTime arrival;
    private int distance;
}
