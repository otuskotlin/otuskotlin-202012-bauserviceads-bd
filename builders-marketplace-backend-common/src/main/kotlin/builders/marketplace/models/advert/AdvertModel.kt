package builders.marketplace.models.advert

import builders.marketplace.models.categories.CategoryId
import builders.marketplace.models.user.UserId
import java.time.Instant

data class AdvertModel(
        val id: AdvertId = AdvertId.NONE,
        val name: String = "",
        val categories: Set<CategoryId> = mutableSetOf(),
        val technicalCharacteristics: Set<TechnicalCharacteristic> = mutableSetOf(),
        val description: String = "",
        val ownerId: UserId = UserId.NONE,
        val createdAt: Long = Instant.now().toEpochMilli(),
        val lastTimeModifiedAt: Long = Instant.now().toEpochMilli(),
        val advertPermission: Set<AdvertPermission> = mutableSetOf(),
        val imagesS3Paths: Set<S3ImagePath> = mutableSetOf(),
        val price: Money = Money.NONE,
        val quantity: Int = 0,
        val manufacturer: String = "",
        val countryOfOrigin: String = "",
        val location: AdvertLocationId = AdvertLocationId.NONE,
        val tags: Set<Tag> = mutableSetOf(),
        val advertType: AdvertType = AdvertType.NA,
        val views: Int = 0
) {
        companion object {
                val NONE = AdvertModel()
        }
}