class EmployeeList : ArrayList<Employee>() {

    fun addEmployee(employee: Employee): Boolean {
        if (this.any { it.id == employee.id }) {
            println("Employee with ID ${employee.id} already exists.")
            return false
        }
        this.add(employee)
        println("Employee added successfully.")
        return true
    }

    fun updateEmployee(id: String, firstName: String, lastName: String): Boolean {
        val emp = this.find { it.id == id } ?: return false
        emp.firstName = firstName
        emp.lastName = lastName
        println("Employee updated.")
        return true
    }

    fun deleteEmployee(id: String): Boolean {
        return this.removeIf { it.id == id }.also {
            if (it) println("Employee deleted.")
            else println("Employee not found.")
        }
    }

    fun getEmployee(id: String): Employee? = this.find { it.id == id }

    override fun toString(): String {
        return if (this.isEmpty()) "No employees found."
        else this.joinToString("\n")
    }
}
