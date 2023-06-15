package domain.usecases.flights.di

import data.airbooking.AirBookingLocalSource
import data.aircraft.AirCraftLocalSource
import data.airport.AirportLocalSource
import data.flight.FlightLocalSource
import domain.datasource.flight.FlightDataSource
import domain.model.Flight

class flightDataDI {
    fun providesFlightsData(): FlightDataSource{
        val airportLocalSource = AirportLocalSource()
        val airBookingLocalSource = AirBookingLocalSource(airportLocalSource)
        val airCraftLocal = AirCraftLocalSource()

        return FlightLocalSource(
            airCraftLocal, airBookingLocalSource
        )
    }
}