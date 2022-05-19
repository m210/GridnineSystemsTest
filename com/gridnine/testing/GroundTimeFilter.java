package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroundTimeFilter implements IFlightFilter {
	
	protected final int hours;

	public GroundTimeFilter(int hours) {
		this.hours = hours;
	}

	@Override
	public boolean test(Flight t) {
		List<Segment> segments = t.getSegments();
		
		/** The flight doesn't have ground time */
		if(segments.size() < 2) {
			return true;
		}
		
		long time = 0;
		for(int i = 0; i < segments.size() - 1; i++) {
			Segment s1 = segments.get(i);
			Segment s2 = segments.get(i + 1);
			
			time += getDifferentInMillis(s1.getArrivalDate(), s2.getDepartureDate());
		}

		return TimeUnit.MILLISECONDS.toHours(time) <= hours;
	}
	
	protected long getDifferentInMillis(LocalDateTime fromDate, LocalDateTime toDate) {
		return Duration.between(fromDate, toDate).toMillis();
	}

}
