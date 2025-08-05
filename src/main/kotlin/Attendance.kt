import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Attendance(
    val id: String,
    val checkInDateTime: LocalDateTime,
    var checkOutDateTime: LocalDateTime? = null
) {
    var totalWorkedTime: String = ""

    fun calculateWorkedDuration() {
        checkOutDateTime?.let {
            val duration = Duration.between(checkInDateTime, it)
            totalWorkedTime = String.format(
                "%02d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()
            )
        }
    }

    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val checkOutStr = checkOutDateTime?.format(formatter) ?: "Still Checked-In"
        return "Employee ID: $id | In: ${checkInDateTime.format(formatter)} | Out: $checkOutStr | Duration: $totalWorkedTime"
    }
}
