package presentation.flight.formats

import domain.model.Flight
import presentation.utils.Formatter
import java.time.format.DateTimeFormatter

class FlightConsoleFormat: Formatter<Flight> {
    override fun format(t: Flight): String {
        val departure = t.departureArrivalBooking.first
        val arrival = t.departureArrivalBooking.second

        return """
            ${t.number}
            Passenger ${departure.airport.name}
            Origin Datetime ${departure.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}
            Arrival ${arrival.airport.name}
            Arrival Datetime ${arrival.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}
            Duration ${t.duration}
            Precio ${t.price}
        """.trimIndent()
    }
}