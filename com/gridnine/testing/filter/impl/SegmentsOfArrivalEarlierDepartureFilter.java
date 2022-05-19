package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.IFlightFilter;
import com.gridnine.testing.entity.Flight;

public class SegmentsOfArrivalEarlierDepartureFilter implements IFlightFilter {

	@Override
	public boolean test(Flight flight) {
		return !flight.getSegments()
				.stream()
				.anyMatch(seg -> seg.getArrivalDate().isBefore(seg.getDepartureDate()));
	}

}
