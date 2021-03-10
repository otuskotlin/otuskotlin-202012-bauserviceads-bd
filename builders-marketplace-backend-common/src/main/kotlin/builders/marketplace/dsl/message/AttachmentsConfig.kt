package builders.marketplace.dsl.message

import builders.marketplace.models.advert.S3ImagePath

class AttachmentsConfig(var files: Set<S3ImagePath> = mutableSetOf())
