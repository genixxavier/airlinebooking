package domain.usecases.seat

import domain.model.seat.SeatSection
import domain.usecases.flights.GetFlightSaved

class GetSeatsSection (
    private  val getFlightSaved: GetFlightSaved
){
    operator fun invoke(): Map<Int, SeatSection>{
        val flight = getFlightSaved()
        return flight.airCraft.seatSections.mapIndexed{ index, seatSection -> index + 1 to seatSection}.toMap()
    }
}