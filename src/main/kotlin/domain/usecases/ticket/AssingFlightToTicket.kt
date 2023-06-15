package domain.usecases.ticket

import domain.datasource.ticket.TicketDataSource
import domain.model.Flight
import domain.model.Ticket

class AssingFlightToTicket(
    private val ticketDataSource: TicketDataSource
) {
    operator fun invoke(flight: Flight?) :Ticket?{
        return flight?.let {
            ticketDataSource.tickets.first().apply {
                this.flight = flight
            }
        }
    }
}