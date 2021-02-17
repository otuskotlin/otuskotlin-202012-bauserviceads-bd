package builders.marketplace.dsl

import builders.marketplace.models.Role

class RoleConfig {
    private val _roles = mutableSetOf<Role>()
    val roles: Set<Role>
        get() = _roles.toSet()

    fun add(role: Role) = _roles.add(role)
}
