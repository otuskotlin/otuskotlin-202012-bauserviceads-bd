package builders.marketplace.backend.mappers

import builders.marketplace.models.advert.AdditionalDetail
import builders.marketplace.models.advert.AdvertPermission
import builders.marketplace.models.advert.S3ImagePath
import builders.marketplace.models.advert.Tag
import builders.marketplace.models.categories.CategoryId
import builders.marketplace.transport.multiplatform.models.advert.options.AdditionalDetailDto
import builders.marketplace.transport.multiplatform.models.advert.options.MarketplaceAdvertPermission

@JvmName("handleCategoryIds")
internal fun Set<CategoryId>.handle(): Set<String>? =
    this.takeIf { categories -> categories.isNotEmpty() }
        ?.filter { categoryId -> categoryId != CategoryId.NONE }
        ?.map { categoryId -> categoryId.id }
        ?.toSet()

@JvmName("handleAdditionalDetails")
internal fun Set<AdditionalDetail>.handle(): Set<AdditionalDetailDto>? =
    this.takeIf { additionalDetails -> additionalDetails.isNotEmpty() }
        ?.filter { additionalDetail -> additionalDetail != AdditionalDetail.NONE }
        ?.map { additionalDetail ->
            AdditionalDetailDto(
                name = additionalDetail.name,
                description = additionalDetail.description
            )
        }?.toSet()

@JvmName("handleS3ImagePaths")
internal fun Set<S3ImagePath>.handle(): Set<String>? =
    this.takeIf { s3imagesPaths -> s3imagesPaths.isNotEmpty() }
        ?.filter { s3imagePath -> s3imagePath != S3ImagePath.NONE }
        ?.map { s3imagePath -> s3imagePath.imagePath }
        ?.toSet()

@JvmName("handleTags")
internal fun Set<Tag>.handle(): Set<String>? =
    this.takeIf { tags -> tags.isNotEmpty() }
        ?.filter { tag -> tag != Tag.NONE }
        ?.map { tag -> tag.name }
        ?.toSet()

@JvmName("handleAdvertPermissions")
internal fun Set<AdvertPermission>.handle(): Set<MarketplaceAdvertPermission>? =
    this.takeIf { advertPermissions -> advertPermissions.isNotEmpty() }
        ?.map { advertPermission -> MarketplaceAdvertPermission.valueOf(advertPermission.name) }
        ?.toSet()

