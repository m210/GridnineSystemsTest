package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlightFinderService {
	
	/** Get all flights from repository */
	
	List<Flight> getAllFlights();
	
	List<Flight> getFlightsAreNotDeparted();
	
	List<Flight> getFlightsAreNotDepartedSince(LocalDateTime time);
	
	List<Flight> getFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate();
	
	List<Flight> getFlightsWithoutGroundTime(int hours);
	
	List<Flight> getFlightsFiltered(IFlightFilter filter);
	
}
