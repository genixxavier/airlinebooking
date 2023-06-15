package domain.model

import java.math.BigDecimal

data class Baggage(
    val name: String,
    val price: BigDecimal,
    val description: String,
    val handQty: Int,
    val checkedQty: Int
)
