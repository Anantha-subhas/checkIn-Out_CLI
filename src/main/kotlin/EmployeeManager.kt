import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class EmployeeManager {
    private val employeeList = EmployeeList()
    private val attendanceList = AttendanceList()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun addEmployee() {
        val employee = Employee.inputEmployee()
        employeeList.addEmployee(employee)
    }

    fun showEmployees() {
        println(employeeList)
    }
    fun checkIn() {
        print("Enter employee ID: ")
        val id = readLine()?.trim().orEmpty()
        val employee = employeeList.getEmployee(id)
        if (employee == null) {
            println("No employee with ID $id.")
            return
        }

        print("Enter check-in date (yyyy-MM-dd) or press Enter for current: ")
        val dateStr = readLine()?.trim().orEmpty()
        val checkInDateTime = parseDateTimeInput(dateStr, isCheckIn = true) ?: return

        val attendance = Attendance(id, checkInDateTime)
        attendanceList.addAttendance(attendance)
    }

    fun checkOut() {
        print("Enter employee ID: ")
        val id = readLine()?.trim().orEmpty()

        print("Enter check-out date (yyyy-MM-dd HH:mm) or press Enter for current: ")
        val dateStr = readLine()?.trim().orEmpty()
        val checkOutDateTime = parseDateTimeInput(dateStr, isCheckIn = false) ?: return

        attendanceList.checkOut(id, checkOutDateTime)
    }

    fun viewAttendance() {
        println(attendanceList)
    }

    private fun parseDateTimeInput(dateStr: String, isCheckIn: Boolean): LocalDateTime? {
        return if (dateStr.isBlank()) {
            // If input is blank, use current time
            LocalDateTime.now()
        } else {
            try {
                if (isCheckIn) {
                    val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    if (date.isAfter(LocalDate.now())) {
                        println("Date cannot be in the future.")
                        null
                    } else {
                        val now = LocalDateTime.now()
                        date.atTime(now.hour, now.minute)
                    }
                } else {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    val dateTime = LocalDateTime.parse(dateStr, formatter)
                    if (dateTime.isAfter(LocalDateTime.now())) {
                        println("Date-time cannot be in the future.")
                        null
                    } else {
                        dateTime
                    }
                }
            } catch (e: DateTimeParseException) {
                if (isCheckIn)
                    println("Invalid check-in date format. Use yyyy-MM-dd.")
                else
                    println("Invalid check-out date format. Use yyyy-MM-dd HH:mm.")
                null
            }
        }
    }

    fun viewWorkingSummary() {
        println("=== Total Working Hours for All Employees ===")
        for (employee in employeeList) {
            val totalTime = attendanceList.getTotalWorkedTimeByEmployee(employee.id)
            println("ID: ${employee.id} | Name: ${employee.firstName} ${employee.lastName} | Total: $totalTime")
        }
    }
}
