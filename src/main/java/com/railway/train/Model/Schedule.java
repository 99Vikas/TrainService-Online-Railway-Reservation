package com.railway.train.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "scheduleInfo")
public class Schedule {

    @Id
    @Indexed
    private String id;
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate tripDate;
    private int firstAcAvailableSeats;
    private int secondAcAvailableSeats;
    private int thirdAcAvailableSeats;
    private int FirstClassAcAvailableSeats;
    private int chairCarAcAvailableSeats;
    private int SleeperAvailableSeats;
    private String tripId;
    private String status;


    public Schedule(LocalDate date, int firstAcSeats, int secondAcSeats, int thirdAcSeats, int firstClassSeats, int chairCarSeats, int sleeperSeats, String tripId, String number, String activeCode) {
        this.tripDate = tripDate;
        this.firstAcAvailableSeats = firstAcAvailableSeats;
        this.secondAcAvailableSeats = secondAcAvailableSeats;
        this.thirdAcAvailableSeats = thirdAcAvailableSeats;
        FirstClassAcAvailableSeats = firstClassSeats;
        this.chairCarAcAvailableSeats = chairCarAcAvailableSeats;
        SleeperAvailableSeats = sleeperSeats;
        this.tripId = tripId;
        this.status = status;
    }

    @Override


    public String toString() {
        return "TripSchedule{" +
                "id='" + id + '\'' +
                ", tripDate=" + tripDate +
                ", firstAcAvailableSeats=" + firstAcAvailableSeats +
                ", secondAcAvailableSeats=" + secondAcAvailableSeats +
                ", thirdAcAvailableSeats=" + thirdAcAvailableSeats +
                ", FirstClassAcAvailableSeats=" + FirstClassAcAvailableSeats +
                ", chairCarAcAvailableSeats=" + chairCarAcAvailableSeats +
                ", SleeperAvailableSeats=" + SleeperAvailableSeats +
                ", tripId='" + tripId + '\'' +
                '}';
    }
}
