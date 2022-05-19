package com.gridnine.testing;

import java.util.List;

public class FlightRepositoryImpl implements IFlightRepository {

	@Override
	public List<Flight> getAllFlights() {
		return FlightBuilder.createFlights();
	}

}
