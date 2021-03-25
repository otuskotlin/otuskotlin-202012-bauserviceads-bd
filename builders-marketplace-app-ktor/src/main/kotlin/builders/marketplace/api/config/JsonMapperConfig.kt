package builders.marketplace.api.config

import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.advert.AdvertListFilterDto
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.LocationDto
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertList
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertUpdate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertDelete
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertList
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertRead
import builders.marketplace.transport.multiplatform.models.advert.response.ResponseAdvertUpdate
import builders.marketplace.transport.multiplatform.models.common.MarketplaceMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val jsonMapperConfig: Json by lazy {
    Json {
        serializersModule = SerializersModule {
            polymorphic(MarketplaceMessage::class) {
                subclass(AdvertDto::class)
                subclass(AdvertListFilterDto::class)
                //options
                subclass(AdditionalDetailDto::class)
                subclass(LocationDto::class)

                subclass(RequestAdvertCreate::class)
                subclass(RequestAdvertUpdate::class)
                subclass(RequestAdvertDelete::class)
                subclass(RequestAdvertRead::class)
                subclass(RequestAdvertList::class)

                subclass(ResponseAdvertCreate::class)
                subclass(ResponseAdvertDelete::class)
                subclass(ResponseAdvertUpdate::class)
                subclass(ResponseAdvertRead::class)
                subclass(ResponseAdvertList::class)
            }
        }
        classDiscriminator = "type"
    }
}