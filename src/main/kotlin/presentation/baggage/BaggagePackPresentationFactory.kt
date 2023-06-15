package presentation.baggage

import domain.model.baggage.pack.BaggagePackage
import presentation.PresentationFactory
import presentation.PresentationFormat
import presentation.baggage.format.BaggagePackageConsoleFormat
import presentation.baggage.types.BaggageTypesConsole
import presentation.utils.Formatter

class BaggagePackPresentationFactory: PresentationFactory<BaggagePackage> {
    override fun getPresentationFormat(format: PresentationFormat): Formatter<BaggagePackage> {
        return BaggagePackageConsoleFormat(
            BaggageTypesConsole()
        )
    }
}