package com.gridnine.testing.repository.impl;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.repository.IFlightRepository;

import java.util.List;

public class FlightRepositoryImpl implements IFlightRepository {

	@Override
	public List<Flight> getAllFlights() {
		return FlightBuilder.createFlights();
	}

}
