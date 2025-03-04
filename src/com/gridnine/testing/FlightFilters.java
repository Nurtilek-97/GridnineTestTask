package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilters {
    public static List<Flight> filterDeparturesBeforeNow(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            boolean hasFutureDeparture = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getDeparture().isBefore(now)) {
                    hasFutureDeparture = false;
                    break;
                }
            }
            if (hasFutureDeparture) {
                result.add(flight);
            }
        }
        return result;
    }

    public static List<Flight> filterArrivalsBeforeDepartures(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            boolean validArrivalTimes = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrival().isBefore(segment.getDeparture())) {
                    validArrivalTimes = false;
                    break;
                }
            }
            if (validArrivalTimes) {
                result.add(flight);
            }
        }
        return result;
    }

    public static List<Flight> filterFlightsWithExcessiveGroundTime(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            long totalGroundTime = 0;
            List<Segment> segments = flight.getSegments();

            for (int i = 0; i < segments.size() - 1; i++) {
                totalGroundTime += Duration.between(segments.get(i).getArrival(), segments.get(i + 1).getDeparture()).toHours();
            }

            if (totalGroundTime <= 2) {
                result.add(flight);
            }
        }
        return result;
    }
}

