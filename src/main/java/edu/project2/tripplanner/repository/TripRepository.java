package edu.project2.tripplanner.repository;

import edu.project2.tripplanner.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    void deleteTripById(Long id);

    List<Trip> findAllByUserId(Long userId);

    Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId);
}
