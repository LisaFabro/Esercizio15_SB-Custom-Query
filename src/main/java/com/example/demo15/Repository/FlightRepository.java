package com.example.demo15.Repository;

import com.example.demo15.Entity.Flight;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT * FROM flight WHERE status = 'ONTIME'", nativeQuery = true)
    List<Flight> retrieveAllFlightsInTime();
    @Query(value = "SELECT * FROM flight ORDER BY from_airport ASC", nativeQuery = true)
    Page<Flight> retrieveAllFlightsOrdered(PageRequest pageRequest);
    @Query(value = "SELECT * FROM flight WHERE description = :p1 OR description = :p2", nativeQuery = true)
    List<Flight> retrieveFlightsWithP1AndP2(String p1, String p2);

}