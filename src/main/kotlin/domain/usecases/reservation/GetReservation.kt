package domain.usecases.reservation

import domain.datasource.ReservationDataSource
import domain.model.Reservation

class GetReservation(
    private val reservationDataSource: ReservationDataSource
) {
    operator fun invoke(): Reservation {
        return reservationDataSource.reservation
    }
}