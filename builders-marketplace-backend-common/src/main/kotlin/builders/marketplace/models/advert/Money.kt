package builders.marketplace.models.advert

import java.math.BigDecimal
import java.util.Currency
import java.util.Locale.UK

data class Money(val currencyCode: Currency = Currency.getInstance(UK), val amount: BigDecimal = BigDecimal.ZERO) {
    companion object {
        val NONE = Money()
    }
}