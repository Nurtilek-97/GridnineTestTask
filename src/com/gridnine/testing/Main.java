package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Flights after filtering out those departing before now:");
        List<Flight> filteredByDeparture = FlightFilters.filterDeparturesBeforeNow(flights);
        filteredByDeparture.forEach(System.out::println);

        System.out.println("\nFlights after filtering out those with arrivals before departures:");
        List<Flight> filteredByArrival = FlightFilters.filterArrivalsBeforeDepartures(filteredByDeparture);
        filteredByArrival.forEach(System.out::println);

        System.out.println("\nFlights after filtering out those with excessive ground time:");
        List<Flight> filteredByGroundTime = FlightFilters.filterFlightsWithExcessiveGroundTime(filteredByArrival);
        filteredByGroundTime.forEach(System.out::println);
    }
}

