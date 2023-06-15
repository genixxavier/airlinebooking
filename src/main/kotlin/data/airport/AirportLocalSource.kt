package data.airport

import domain.datasource.airport.AirportDataSource
import domain.model.Airport

class AirportLocalSource: AirportDataSource {
    override fun getAirports(): List<Airport> = listOf(
        Airport("QMX", "Mexico City"),
        Airport("CHI", "Chicago"),
        Airport("BOG", "Bogota"),
        Airport("CUN", "Cancun"),
    )
}