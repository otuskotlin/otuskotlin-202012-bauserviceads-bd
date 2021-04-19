package builders.marketplace.models.advert

data class AdvertError(
    val field: String = "",
    val level: AdvertErrorLevel = AdvertErrorLevel.NONE,
    val message: String = ""
) {
    enum class AdvertErrorLevel {
        NONE,
        INFO,
        WARNING,
        ERROR,
        FATAL;

        val isError: Boolean
            get() {
                return when (this) {
                    NONE, INFO, WARNING -> false
                    ERROR, FATAL -> true
                }
            }

    }
}
