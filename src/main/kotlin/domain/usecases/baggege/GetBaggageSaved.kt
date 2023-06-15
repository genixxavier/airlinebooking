package domain.usecases.baggege

import domain.datasource.ticket.TicketDataSource
import domain.model.baggage.pack.BaggagePackage

class GetBaggageSaved(
    private val ticketDataSource: TicketDataSource
) {
    operator fun invoke(): BaggagePackage {
        return ticketDataSource.tickets.first().baggagePackage
    }
}