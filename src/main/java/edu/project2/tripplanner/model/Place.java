package edu.project2.tripplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "places")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "town", nullable = false)
    private String town;

    private int rate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Trip trip;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Comment> list_of_comments = new ArrayList<>();

}
