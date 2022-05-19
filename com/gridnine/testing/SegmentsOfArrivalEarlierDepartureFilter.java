package com.gridnine.testing;

public class SegmentsOfArrivalEarlierDepartureFilter implements IFlightFilter {

	@Override
	public boolean test(Flight flight) {
		return !flight.getSegments()
				.stream()
				.anyMatch(seg -> seg.getArrivalDate().isBefore(seg.getDepartureDate()));
	}

}
