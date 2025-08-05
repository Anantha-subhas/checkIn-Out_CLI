fun main() {
    val manager = EmployeeManager()

    while (true) {
        println(
            """
    1. Add Employee
    2. Show All Employees
    3. Check-In
    4. Check-Out
    5. View Attendance
    6. View Total Working Summary
    7. Exit
""".trimIndent()
        )

        print("Choose: ")
        when (readLine()?.trim()) {
            "1" -> manager.addEmployee()
            "2" -> manager.showEmployees()
            "3" -> manager.checkIn()
            "4" -> manager.checkOut()
            "5" -> manager.viewAttendance()
            "6" -> manager.viewWorkingSummary()
            "7" -> return
            else -> println("Invalid choice.")
        }
    }
}
