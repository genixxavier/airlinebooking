package domain.model

import java.math.BigDecimal

class Reservation{
    lateinit var code: String
    var departureTickets: List<Ticket> = mutableListOf()
    var returnTickets: List<Ticket> = mutableListOf()
    val total: BigDecimal
        get() {
            return departureTickets.sumOf {
                it.priceTotal
            }.plus(
                returnTickets.sumOf { it.priceTotal }
            )
        }
}
