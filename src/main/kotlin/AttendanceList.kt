import java.time.LocalDateTime
import java.time.Duration

class AttendanceList : ArrayList<Attendance>() {

    fun addAttendance(attendance: Attendance): Boolean {
        if (this.any { it.id == attendance.id && it.checkOutDateTime == null }) {
            println("Open attendance already exists for ${attendance.id}")
            return false
        }
        this.add(attendance)
        println("Check-in recorded.")
        return true
    }

    fun checkOut(id: String, checkoutTime: LocalDateTime): Boolean {
        val record = this.find { it.id == id && it.checkOutDateTime == null }
        return if (record != null && checkoutTime.isAfter(record.checkInDateTime)) {
            record.checkOutDateTime = checkoutTime
            record.calculateWorkedDuration()
            println("Check-out successful.")
            true
        } else {
            println("No open check-in found or invalid time.")
            false
        }
    }

    fun deleteAttendance(id: String, checkInTime: LocalDateTime): Boolean {
        return this.removeIf { it.id == id && it.checkInDateTime == checkInTime }
    }

    fun getActiveAttendances(): List<Attendance> {
        return this.filter { it.checkOutDateTime == null }
    }


    fun getTotalWorkedTimeByEmployee(id: String): String {
        val totalDuration = this
            .filter { it.id == id && it.checkOutDateTime != null }
            .map {
                Duration.between(it.checkInDateTime, it.checkOutDateTime)
            }
            .fold(Duration.ZERO) { acc, d -> acc.plus(d) }

        return String.format(
            "%02d:%02d:%02d",
            totalDuration.toHours(),
            totalDuration.toMinutesPart(),
            totalDuration.toSecondsPart()
        )
    }

    // ✅ New method added here:
    fun getLastUnclosedAttendance(id: String): Attendance? {
        return this
            .filter { it.id == id && it.checkOutDateTime == null }
            .maxByOrNull { it.checkInDateTime }
    }

    override fun toString(): String {
        return if (this.isEmpty()) "No attendance records." else this.joinToString("\n")
    }
}
