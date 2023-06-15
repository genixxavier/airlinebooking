package presentation.ticket.formats

import domain.model.Ticket
import presentation.utils.Formatter

class TicketHTMLFormat: Formatter<Ticket> {
    override fun format(t: Ticket): String {
        return """
            <div><p>Passenger ${t.passenger.name}</p>
            <p>Flight Number: ${t.flight.number}</p>
            <p>Departure: ${t.flight.departureArrivalBooking.first.airport.name}</p>
            <p>Arrival: ${t.flight.departureArrivalBooking.second.airport.name}</p>
            <p>Flight price: $${t.flight.price}</p>
            <p>Seat: ${t.seat.number}</p>
            <p>PriceTotal $${t.priceTotal}</p></div>
        """.trimIndent()
    }
}