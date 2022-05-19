package com.gridnine.testing.service.impl;

import com.gridnine.testing.filter.impl.AlreadyDepartedFilter;
import com.gridnine.testing.filter.impl.GroundTimeFilter;
import com.gridnine.testing.filter.IFlightFilter;
import com.gridnine.testing.filter.impl.SegmentsOfArrivalEarlierDepartureFilter;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.repository.IFlightRepository;
import com.gridnine.testing.service.IFlightFinderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFinderServiceImpl implements IFlightFinderService {
	
	private final IFlightRepository repository;
	
	public FlightFinderServiceImpl(IFlightRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Flight> getAllFlights() {
		return new ArrayList<Flight>(repository.getAllFlights());
	}

	@Override
	public List<Flight> getFlightsAreNotDeparted() {
		return getAllFlights().stream()
				.filter(a -> a.getSegments().size() > 0)
				.filter(new AlreadyDepartedFilter(LocalDateTime.now()))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Flight> getFlightsAreNotDepartedSince(LocalDateTime time) {
		return getAllFlights().stream()
				.filter(fl -> fl.getSegments().size() > 0)
				.filter(new AlreadyDepartedFilter(time))
				.collect(Collectors.toList());
	}

	@Override
	public List<Flight> getFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate() {
		return getAllFlights().stream()
				.filter(fl -> fl.getSegments().size() > 0)
				.filter(new SegmentsOfArrivalEarlierDepartureFilter())
				.collect(Collectors.toList());
	}

	@Override
	public List<Flight> getFlightsWithoutGroundTime(int hours) {
		return getAllFlights().stream()
				.filter(fl -> fl.getSegments().size() > 0)
				.filter(new GroundTimeFilter(hours))
				.collect(Collectors.toList());			
	}

	@Override
	public List<Flight> getFlightsFiltered(IFlightFilter filter) {
		if(filter == null) {
			return new ArrayList<Flight>();
		}
		
		return getAllFlights().stream()
				.filter(fl -> fl.getSegments().size() > 0)
				.filter(filter)
				.collect(Collectors.toList());		
	}

}
