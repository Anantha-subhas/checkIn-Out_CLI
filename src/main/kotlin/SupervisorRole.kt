enum class SupervisorRole(val displayName: String) {
    MANAGER("Manager"),
    CEO("CEO");

    override fun toString(): String = displayName
}