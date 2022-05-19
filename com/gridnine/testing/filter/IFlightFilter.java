package com.gridnine.testing.filter;

import com.gridnine.testing.entity.Flight;

import java.util.function.Predicate;

public interface IFlightFilter extends Predicate<Flight> {
	
}
