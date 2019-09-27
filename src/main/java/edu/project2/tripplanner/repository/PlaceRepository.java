package edu.project2.tripplanner.repository;

import edu.project2.tripplanner.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    boolean existsById(Long id);

    Optional<Place> findById(Long id);
}
