package org.daylight.routenavigator.backend.repositories;

import org.daylight.routenavigator.backend.entities.Location;
import org.daylight.routenavigator.backend.entities.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findAllByDepartureLocation(Location departureLocation);

    List<Route> findAllByArrivalLocation(Location arrivalLocation);

    @Query("SELECT r FROM Route r WHERE r.departureTime >= :departureTime ORDER BY r.departureTime ASC")
    List<Route> findAllByDepartureTimeAfter(OffsetDateTime departureTime);

    @Query("SELECT r FROM Route r WHERE r.arrivalTime >= :arrivalTime ORDER BY r.arrivalTime ASC")
    List<Route> findAllByArrivalTimeAfter(OffsetDateTime arrivalTime);

    @Query("SELECT r FROM Route r WHERE r.departureLocation = :departureLocation and r.departureTime >= :departureTime ORDER BY r.departureTime ASC")
    List<Route> findAllByDepartureLocationAndDepartureTimeAfter(Location departureLocation, OffsetDateTime departureTime);

    @Query("SELECT r FROM Route r WHERE r.arrivalLocation = :arrivalLocation and r.arrivalTime <= :arrivalTime ORDER BY r.departureTime ASC")
    List<Route> findAllByArrivalLocationAndArrivalTimeBefore(Location arrivalLocation, OffsetDateTime arrivalTime);
}
