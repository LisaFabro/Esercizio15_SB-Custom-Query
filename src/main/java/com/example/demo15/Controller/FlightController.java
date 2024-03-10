package com.example.demo15.Controller;

import com.example.demo15.Entity.Flight;
import com.example.demo15.Repository.FlightRepository;
import com.example.demo15.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("flights")

public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository flightRepository;
    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addFlights(){
        return ResponseEntity.ok().body(flightService.addFlights());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok().body(flightRepository.findAll());
    }

    //Provisioning dei voli: Implementa un metodo per fornire n voli. Il numero n dovrebbe essere un parametro di query opzionale.
    //Se il parametro è assente, n dovrebbe avere un valore predefinito di 100. Genera valori casuali per i campi stringa
    //e genera casualmente lo stato per ogni volo. (CUSTOM QUERIES 2)
    @PostMapping("/departings/{input}")
    public ResponseEntity<List<Flight>> departingsFlights(@PathVariable int input){
        return ResponseEntity.ok().body(flightService.addFlightsWithInput(input));
    }

    //Recupero di tutti i voli con la paginazione: Implementa un metodo per recuperare tutti i voli dal database utilizzando
    // la paginazione e restituirli in ordine crescente per il campo fromAirport. (CUSTOM QUESRIES 2)

    @GetMapping("/fromairport")
    public ResponseEntity<Page<Flight>> flightsFromAirport(){
        Page<Flight> response = flightRepository.retrieveAllFlightsOrdered(PageRequest.of(0,10));
        return ResponseEntity.ok().body(response);
    }

    //Recupero dei voli ONTIME senza query personalizzata: Implementa un metodo per recuperare tutti i voli
    // che sono ONTIME senza utilizzare una query personalizzata. (CUSTOM QUESRIES 2)
    @GetMapping("/ontime")
    public  ResponseEntity<List<Flight>> getFlightsOnTime(){
      return ResponseEntity.ok().body(flightRepository.retrieveAllFlightsInTime());
    }

    //Recupero dei voli con query personalizzata: Implementa un metodo per recuperare tutti i voli che sono
    // nello stato p1 o p2 utilizzando una query personalizzata. L'utente dovrebbe passare p1 e p2
    // come parametri alla richiesta GET. (CUSTOM QUESRIES 2)
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam (name = "p1") String p1, @RequestParam (name = "p2") String p2){
        return ResponseEntity.ok().body(flightRepository.retrieveFlightsWithP1AndP2(p1, p2));
    }
}
