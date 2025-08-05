class Employee(
    val id: String,
    var firstName: String,
    var lastName: String,
    var role: Role,
    var reportTo: SupervisorRole
) {
    companion object {
        private var counter = 1

        fun generateId(): String {
            return "E" + counter++.toString().padStart(3, '0')
        }

        fun inputEmployee(): Employee {
            println("=== Add New Employee ===")

            var firstName: String
            do {
                print("Enter First Name: ")
                firstName = readLine()?.trim().orEmpty()
                if (firstName.isBlank()) println("First name cannot be empty.")
            } while (firstName.isBlank())

            var lastName: String
            do {
                print("Enter Last Name: ")
                lastName = readLine()?.trim().orEmpty()
                if (lastName.isBlank()) println("Last name cannot be empty.")
            } while (lastName.isBlank())

            println("Select Role:")
            Role.entries.forEachIndexed { i, r -> println("${i + 1}. $r") }
            val role = Role.entries.getOrNull(readLine()?.toIntOrNull()?.minus(1) ?: -1)
                ?: throw IllegalArgumentException("Invalid role selected")

            println("Select Supervisor:")
            SupervisorRole.entries.forEachIndexed { i, s -> println("${i + 1}. $s") }
            val supervisor = SupervisorRole.entries.getOrNull(readLine()?.toIntOrNull()?.minus(1) ?: -1)
                ?: throw IllegalArgumentException("Invalid supervisor selected")

            val id = generateId()
            println("Generated Employee ID: $id")
            return Employee(id, firstName, lastName, role, supervisor)
        }
    }

    override fun toString(): String {
        return "ID: $id | Name: $firstName $lastName | Role: $role | Reports To: $reportTo"
    }
}
