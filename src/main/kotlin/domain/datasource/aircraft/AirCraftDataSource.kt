package domain.datasource.aircraft

import domain.model.AirCraft

interface AirCraftDataSource {
    fun getAirCrafts(): List<AirCraft>
}