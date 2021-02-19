package builders.marketplace.dsl.message

import builders.marketplace.models.advert.S3ImagePath

class AttachmentsConfig {
    lateinit var files: Set<S3ImagePath>
}
