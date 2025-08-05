enum class Department(val displayName: String) {
    CSE("Computer Science and Engineering"),
    IT("Information Technology"),
    ECE("Electronics and Communication Engineering"),
    EEE("Electrical and Electronics Engineering"),
    MECH("Mechanical Engineering"),
    CIVIL("Civil Engineering");

    override fun toString(): String = displayName
}
