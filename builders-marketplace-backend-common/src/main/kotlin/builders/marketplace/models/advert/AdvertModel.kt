package builders.marketplace.models.advert

import builders.marketplace.models.categories.CategoryId
import builders.marketplace.models.user.UserId
import java.time.Instant

data class AdvertModel(
        val id: AdvertId = AdvertId.NONE,
        val name: String,
        val categories: Set<CategoryId>,
        val technicalCharacteristics: Set<TechnicalCharacteristic> = emptySet(),
        val description: String,
        val ownerId: UserId,
        val createdAt: Long = Instant.now().toEpochMilli(),
        val lastTimeModifiedAt: Long,
        val imagesS3Paths: Set<S3ImagePath>,
        val tags: Set<Tag>,
        val views: Int = 0
)