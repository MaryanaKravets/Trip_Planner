package edu.project2.tripplanner.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "places")
@NoArgsConstructor
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country",nullable = false)
    private String country;

    @Column(name = "town", nullable = false)
    private String town;

    private int rate;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> listOfComments;

}
