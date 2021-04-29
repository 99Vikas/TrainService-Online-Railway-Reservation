package com.railway.train.Model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("stations")
public class Station {

    @Id
    private String id;
    @Indexed
    private String code;
    private String name;
    private String zone;
    private String state;

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
