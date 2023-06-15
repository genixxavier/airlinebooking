package domain.usecases.ticket

import domain.datasource.ticket.TicketDataSource
import domain.model.Ticket
import domain.model.seat.Seat

class AssignSeatTicket(
    private val ticketDataSource: TicketDataSource
) {
    operator fun invoke(seat: Seat?): Ticket?{
        return seat?.let {
            ticketDataSource.tickets.first().apply {
                this.seat = seat
            }
        }
    }
}