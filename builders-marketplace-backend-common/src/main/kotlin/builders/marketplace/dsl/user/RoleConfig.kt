package builders.marketplace.dsl.user

import builders.marketplace.models.user.Role

class RoleConfig {
    private val _roles = mutableSetOf<Role>()
    val roles: Set<Role>
        get() = _roles.toSet()

    fun add(role: Role) = _roles.add(role)
}
