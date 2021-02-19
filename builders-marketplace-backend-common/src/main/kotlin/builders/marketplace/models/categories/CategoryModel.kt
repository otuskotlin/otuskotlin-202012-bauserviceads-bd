package builders.marketplace.models.categories

data class CategoryModel(
        val id: CategoryId = CategoryId.NONE,
        val name: String,
        val subCategories: Set<SubCategoryId>
)