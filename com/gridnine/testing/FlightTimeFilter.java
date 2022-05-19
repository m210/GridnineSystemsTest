package com.gridnine.testing;

import java.util.concurrent.TimeUnit;

public class FlightTimeFilter extends GroundTimeFilter {
	
	public FlightTimeFilter(int hours) {
		super(hours);
	}

	@Override
	public boolean test(Flight flight) {
		long time = TimeUnit.MILLISECONDS.toHours(flight.getSegments()
				.stream()
				.mapToLong(e -> getDifferentInMillis(e.getDepartureDate(), e.getArrivalDate()))
				.sum());
		
		return time > 0 && time <= hours;
	}

}
