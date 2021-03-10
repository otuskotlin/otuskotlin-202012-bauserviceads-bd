package builders.marketplace.context

import builders.marketplace.models.advert.AdvertId
import builders.marketplace.models.advert.AdvertModel

data class AdvertBackendContext(
    var requestAdvertId: AdvertId = AdvertId.NONE,
    var requestAdvert: AdvertModel = AdvertModel.NONE,
    var advertResponse: AdvertModel = AdvertModel.NONE
)