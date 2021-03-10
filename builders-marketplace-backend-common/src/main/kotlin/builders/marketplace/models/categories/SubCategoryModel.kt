package builders.marketplace.models.categories

data class SubCategoryModel(
        val id: SubCategoryId = SubCategoryId.NONE,
        val name: String = "",
        val parentCategoryId: CategoryId = CategoryId.NONE
)