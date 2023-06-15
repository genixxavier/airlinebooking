package data.baggage

import domain.model.baggage.pack.BaggagePackage
import domain.model.baggage.pack.vclub.ClubBasic
import domain.model.baggage.pack.vclub.ClubClassic
import domain.model.baggage.pack.vclub.ClubPlus

class BaggageRegularLocalSource: BaggagePackageLocalSource() {
    override fun getBaggagePacks(): Map<Int, BaggagePackage> {
        return mapOf(
            1 to ClubBasic(price),
            1 to ClubClassic(price),
            1 to ClubPlus(price),
        )
    }
}