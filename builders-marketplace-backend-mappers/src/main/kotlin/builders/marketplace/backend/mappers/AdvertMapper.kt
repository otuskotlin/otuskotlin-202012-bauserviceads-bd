package builders.marketplace.backend.mappers

import builders.marketplace.context.AdvertBackendContext
import builders.marketplace.models.advert.AdvertId
import builders.marketplace.models.advert.AdvertModel
import builders.marketplace.models.advert.TechnicalCharacteristic
import builders.marketplace.models.advert.TechnicalCharacteristicId
import builders.marketplace.models.advert.TechnicalParameter
import builders.marketplace.models.advert.TechnicalParameterId
import builders.marketplace.models.advert.UnitType
import builders.marketplace.models.advert.UnitTypeId
import builders.marketplace.models.categories.CategoryId
import builders.marketplace.transport.multiplatform.models.advert.AdvertCreateDto
import builders.marketplace.transport.multiplatform.models.advert.AdvertDto
import builders.marketplace.transport.multiplatform.models.advert.options.TechParamDto
import builders.marketplace.transport.multiplatform.models.advert.options.TechnicalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.UnitTypeDto
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertCreate
import builders.marketplace.transport.multiplatform.models.advert.request.RequestAdvertRead

fun AdvertBackendContext.respondTo(advertTransportData: RequestAdvertCreate) = apply {
    this.requestAdvert = advertTransportData.buyAdvertCreateData?.toInternalAdvertModel() ?: AdvertModel.NONE
}

fun AdvertBackendContext.respondTo(advertTransportData: RequestAdvertRead) = apply {
    this.requestAdvertId = advertTransportData.advertId?.let { advertId -> AdvertId(advertId) } ?: AdvertId.NONE
}

fun AdvertDto.toInternalAdvertModel() = AdvertModel(
    id = this.id?.let { id -> AdvertId(id) } ?: AdvertId.NONE,
    name = this.name ?: "",
    categories = this.categories?.map { categoryId -> CategoryId(categoryId) }?.toMutableSet() ?: mutableSetOf(),
    technicalCharacteristics = this.technicalDetails?.map {
            technicalDetailDto: TechnicalDetailDto -> technicalDetailDto.toInternalTechnicalCharacteristic()
    }?.toMutableSet() ?: mutableSetOf()
)

fun AdvertCreateDto.toInternalAdvertModel() = AdvertModel(
    name = this.name ?: "",
    categories = this.categories?.map { categoryId -> CategoryId(categoryId) }?.toMutableSet() ?: mutableSetOf(),
    technicalCharacteristics = this.technicalDetails?.map {
            technicalDetailDto: TechnicalDetailDto -> technicalDetailDto.toInternalTechnicalCharacteristic()
    }?.toMutableSet() ?: mutableSetOf()
)

fun TechnicalDetailDto.toInternalTechnicalCharacteristic() = TechnicalCharacteristic(
    id = this.id?.let { id -> TechnicalCharacteristicId(id) } ?: TechnicalCharacteristicId.NONE,
    value = this.value ?: "",
    comparableValue = this.comparableValue ?: Double.MIN_VALUE,
    technicalParameter = this.param?.let { techParamDto: TechParamDto ->
        techParamDto.toInternalTechnicalParameter()
    } ?: TechnicalParameter.NONE,
    unit = this.unit?.let { unitTypeDto: UnitTypeDto ->
        unitTypeDto.toInternalUnitType()
    } ?: UnitType.NONE
)

fun TechParamDto.toInternalTechnicalParameter() = TechnicalParameter(
    id = this.id?.let { id -> TechnicalParameterId(id) } ?: TechnicalParameterId.NONE,
    name = this.name ?: "",
    description = this.description ?: "",
    priority = this.priority ?: Double.MIN_VALUE,
    units = this.units?.map { unitTypeDto: UnitTypeDto -> unitTypeDto.toInternalUnitType() }?.toMutableSet()
        ?: mutableSetOf()
)

fun UnitTypeDto.toInternalUnitType() = UnitType(
    id = this.id?.let { id -> UnitTypeId(id) } ?: UnitTypeId.NONE,
    name = this.name ?: "",
    description = this.description ?: "",
    symbol = this.symbol ?: "",
    symbols = this.symbols?.toMutableSet() ?: mutableSetOf(),
    isBase = this.isBase ?: false
)
