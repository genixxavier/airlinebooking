package domain.usecases.baggege

import domain.datasource.baggage.BaggagePackageDataSource
import domain.model.baggage.pack.BaggagePackage

class GetBaggagePackage(
    private val baggagePackageDataSource: BaggagePackageDataSource
) {
    operator fun invoke(): Map<Int, BaggagePackage>{
        return baggagePackageDataSource.getBaggagePacks()
    }
}