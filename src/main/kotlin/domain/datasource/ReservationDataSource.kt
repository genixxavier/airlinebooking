package domain.datasource

import domain.model.Reservation

interface ReservationDataSource {
    val reservation: Reservation
}