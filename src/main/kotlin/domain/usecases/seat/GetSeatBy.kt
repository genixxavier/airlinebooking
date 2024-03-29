package domain.usecases.seat

import domain.model.seat.Seat
import domain.model.seat.SeatSection
import domain.model.seat.SeatStatus

class GetSeatBy {
    operator fun invoke(seatSection: SeatSection?): Map<Int, Seat>{
        return seatSection?.seats?.filter { it.status == SeatStatus.AVAILABLE }?.mapIndexed {
            index, seat -> index +1 to seat
        }?.toMap() ?: mapOf()
    }
}