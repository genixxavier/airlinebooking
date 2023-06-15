package presentation.baggage.format

import domain.model.baggage.pack.BaggagePackage
import domain.model.baggage.type.BaggageType
import presentation.utils.Formatter

class BaggagePackageConsoleFormat(
    private val baggageType: Formatter<BaggageType>
) : Formatter<BaggagePackage> {
    override fun format(t: BaggagePackage): String {
        return """
${t.name}
${baggageType.format(t.baggageType).trim()}
Boarding Turn: ${t.boardingTurn.name}
Price: $${t.price}

        """.trimIndent()
    }
}