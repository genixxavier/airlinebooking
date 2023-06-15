package presentation.flight.formats

import domain.model.Flight
import presentation.utils.Formatter
import java.time.format.DateTimeFormatter

class FlightHTMLFormat: Formatter<Flight> {
    override fun format(t: Flight): String {
        val departure = t.departureArrivalBooking.first
        val arrival = t.departureArrivalBooking.second

        return """
            <div><p>${t.number}</p>
            <p>Passenger ${departure.airport.name}</p>
            <p>Origin Datetime ${departure.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}</p>
            <p>Arrival ${arrival.airport.name}</p>
            <p>Arrival Datetime ${arrival.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}</p>
            <p>Duration ${t.duration}</p>
            <p>Precio ${t.price}</p></div>
        """.trimIndent()
    }
}