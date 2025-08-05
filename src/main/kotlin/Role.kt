enum class Role(val displayName: String) {
    DEVELOPER("Developer"),
    TESTER("Tester"),
    MANAGER("Manager"),
    DESIGNER("Designer");

    override fun toString(): String = displayName
}