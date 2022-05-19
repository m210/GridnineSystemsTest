package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.IFlightFilter;
import com.gridnine.testing.entity.Flight;

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
