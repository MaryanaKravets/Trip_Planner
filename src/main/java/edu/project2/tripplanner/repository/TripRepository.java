package edu.project2.tripplanner.repository;

import edu.project2.tripplanner.model.Place;
import edu.project2.tripplanner.model.Transport;
import edu.project2.tripplanner.model.Trip;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

    void deleteTripById(Long id);

//    @Transactional
//    @Modifying
//    @Query(value = "insert into trips (departure_day,day_of_arrival,mode_of_transport) values (:departure_day,:day_of_arrival,:mode_of_transport)", nativeQuery = true)
//    void addTrip(@RequestParam("departure_day") LocalDate departure_day,
//                 @RequestParam("day_of_arrival") LocalDate day_of_arrival,
//                 @RequestParam("mode_of_transport") Transport mode_of_transport);

    @Transactional
    @Modifying
    @Query(value = "update trips t set t.departure_day=:departure_day, t.day_of_arrival=:day_of_arrival," +
            " t.mode_of_transport=:mode_of_transport where t.id=:id")
    void editTrip(@Param("departure_day") LocalDate departure_day,
                  @Param("day_of_arrival") LocalDate day_of_arrival,
                  @Param("mode_of_transport") String mode_of_transport,
//                  @Param("placeId") Long placeId,
                  @Param("id") Long id);

    @Query(value = "insert into trips (placeSet) select ")
    void addPlaceToTrip(Long tripId, Place place);


    @Query(value = "select all from trips t where t.user_id=:userId")
    List<Trip> findAllByUserId(@Param("userId") Long userId);


    Optional<Trip> findTripByIdAndUserId(Long userId, Long tripId);
}
