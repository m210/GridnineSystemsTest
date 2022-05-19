package com.gridnine.testing;

import com.gridnine.testing.controller.FlightFinderController;
import com.gridnine.testing.exception.FlightNotFoundException;
import com.gridnine.testing.filter.impl.FlightTimeFilter;
import com.gridnine.testing.repository.impl.FlightRepositoryImpl;
import com.gridnine.testing.repository.IFlightRepository;
import com.gridnine.testing.service.IFlightFinderService;
import com.gridnine.testing.service.impl.FlightFinderServiceImpl;

import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {
		/** Create Bean of the test repository with FlightBuilder.createFlights() factory */
		
		IFlightRepository repository = new FlightRepositoryImpl();
		
		/** Create Bean of the service that will get flights from repository */
		
		IFlightFinderService service = new FlightFinderServiceImpl(repository);
		
		/** Create Bean of controller that will send queries from user to service */
		
		FlightFinderController controller = new FlightFinderController(service);
		
		/** Task0: User's query to print all flights */
		
		System.out.println("printAllFlights() query:");
		controller.printAllFlights(); //If this query will throw an exception, others queries will be useless, so I didn't catch the exception.
		
		/** Task1: Вылет до текущего момента времени */
		
		System.out.println("printFlightsAreNotDeparted() query:");
		try {
			controller.printFlightsAreNotDeparted();
		} catch(FlightNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("printFlightsAreNotDeparted(time) query:");
		try {
			controller.printFlightsAreNotDeparted(LocalDateTime.of(2022, 5, 22, 11, 00));
		} catch(FlightNotFoundException e) {
			e.printStackTrace();
		}
		
		/** Task2: Имеются сегменты с датой прилёта раньше даты вылета */
		
		System.out.println("printFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate() query:");
		try {
			controller.printFlightsWithoutSegmentsOfArrivalDateEarlierDepartureDate();
		} catch(FlightNotFoundException e) {
			e.printStackTrace();
		}
		
		/** Task3: Общее время, проведённое на земле превышает два часа 
		 * (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним) 
		*/
		
		System.out.println("printFlightsWithoutGroundTime(2) query:");
		try {
			controller.printFlightsWithoutGroundTime(2);
		} catch(FlightNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("________________________");
		System.out.println("Another custom filter (Flight time not more than 3 hours)");
		try {
			controller.printFlightsFiltered(new FlightTimeFilter(3));
		} catch(FlightNotFoundException e) {
			e.printStackTrace();
		}
	}

}
