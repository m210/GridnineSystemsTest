package com.gridnine.testing.controller;

import com.gridnine.testing.exception.FlightNotFoundException;
import com.gridnine.testing.filter.IFlightFilter;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.IFlightFinderService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFinderController {
	
	private final IFlightFinderService service;
	
	public FlightFinderController(IFlightFinderService service) {
		this.service = service;
	}

	public void printAllFlights() {
		printFlights(service.getAllFlights());
	}

	public void printFlightsAreNotDeparted() {
		printFlights(service.getFlightsAreNotDeparted());
	}
	
	public void printFlightsAreNotDeparted(LocalDateTime time) {
		printFlights(service.getFlightsAreNotDepartedSince(time));
	}

	public void printFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate() {
		printFlights(service.getFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate());
	}

	public void printFlightsWithoutGroundTime(int hours) {
		printFlights(service.getFlightsWithoutGroundTime(hours));
	}
	
	public void printFlightsFiltered(IFlightFilter filter) {
		printFlights(service.getFlightsFiltered(filter));
	}
	
	private void printFlights(List<Flight> flights) {
		if(flights == null || flights.size() < 1) {
			throw new FlightNotFoundException(flights == null ?
					"The flight list shouldn't be null." : 
						"The flights of your query are not found.");
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("Your query has ");
		builder.append(flights.size());
		builder.append(" flights:\n");
		
		int index = 1;
		for(Flight flight : flights) {
			builder.append("\t");
			builder.append(index++);
			builder.append(": ");
			builder.append(flight);
			builder.append("\n");
		}
		System.out.println(builder);
	}
	
}
