package com.gridnine.testing;

import java.time.LocalDateTime;

public class AlreadyDepartedFilter implements IFlightFilter {
	
	private final LocalDateTime time;
	
	public AlreadyDepartedFilter(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public boolean test(Flight flight) {
		return !time.isAfter(flight.getSegments().get(0).getDepartureDate());
	}

}
