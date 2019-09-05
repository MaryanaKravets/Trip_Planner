package edu.project2.tripplanner.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "trips")
@NoArgsConstructor
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "departure_day", nullable = false)
    private LocalDate departureDay;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "day_of_arrival", nullable = false)
    private LocalDate dayOfArrival;

    @Column(name = "mode_of_transport", nullable = false)
    @Enumerated(EnumType.STRING)
    Transport transports;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "price_of_trip")

    private int price;

    @OneToMany( cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Place> placeSet;

}
