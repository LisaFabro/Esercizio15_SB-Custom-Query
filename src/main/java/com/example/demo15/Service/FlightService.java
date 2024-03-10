package com.example.demo15.Service;

import com.example.demo15.Entity.Flight;
import com.example.demo15.Entity.StatusType;
import com.example.demo15.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> addFlights(){
        List<Flight> newListFlight = new ArrayList<>();
        for(int i= 1 ; i<51 ; i++){
            Flight flight = new Flight();
            flight.setDescription(getRandomString());
            flight.setToAirport(getRandomString());
            flight.setFromAirport(getRandomString());
            flight.setStatus(StatusType.ONTIME);
            newListFlight.add(flight);
        }
        return flightRepository.saveAll(newListFlight);
    }
    public List<Flight> addFlightsWithInput(Integer input){
        List<Flight> newListFlight = new ArrayList<>();
        if(input != null){
        for(int i= 1 ; i<=input ; i++){
            Flight flight = new Flight();
            flight.setDescription(getRandomString());
            flight.setToAirport(getRandomString());
            flight.setFromAirport(getRandomString());
            flight.setStatus(getRandomStatusType());
            newListFlight.add(flight);
        }
        }else {
            for(int i= 1 ; i<=100 ; i++){
                Flight flight = new Flight();
                flight.setDescription(getRandomString());
                flight.setToAirport(getRandomString());
                flight.setFromAirport(getRandomString());
                flight.setStatus(getRandomStatusType());
                newListFlight.add(flight);
            }
        }
        return flightRepository.saveAll(newListFlight);
    }

    public String getRandomString(){
        Random random = new Random();
        return  random.ints(97,122)
                .limit(7)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }
    public StatusType getRandomStatusType(){
        Random random = new Random();
        Integer value = random.ints(1,4).limit(1).findFirst().getAsInt();
        StatusType getStatusType = null;
        switch (value){
            case 1:
                getStatusType = StatusType.ONTIME;
                break;
            case 2:
                getStatusType = StatusType.DELAYED;
                break;
            case 3:
                getStatusType = StatusType.CANCELLED;
                break;
        }
        return getStatusType;
    }
}
