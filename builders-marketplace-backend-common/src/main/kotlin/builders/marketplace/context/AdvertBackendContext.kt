package builders.marketplace.context

import builders.marketplace.models.advert.AdvertError
import builders.marketplace.models.advert.AdvertId
import builders.marketplace.models.advert.AdvertModel
import java.time.Instant

data class AdvertBackendContext(
    var startedAt: Instant = Instant.MIN,
    var requestAdvertId: AdvertId = AdvertId.NONE,
    var requestAdvert: AdvertModel = AdvertModel.NONE,
    var advertResponse: AdvertModel = AdvertModel.NONE,
    var responseId: String = "",
    var errors: List<AdvertError> = listOf(),
    var status: AdvertBackendContextStatus = AdvertBackendContextStatus.NONE,

    var stubCase: AdvertStubCase = AdvertStubCase.NONE
)