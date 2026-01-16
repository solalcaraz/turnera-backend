package domain.appointment

import domain.Patient
import domain.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Appointment(
    val service: Service,
    val patient: Patient,
    val startDate: LocalDate,
    val startTime: LocalTime,
    val patientNote: String?
){
    var status: AppointmentStatus = AppointmentStatus.RESERVED

    init {
        require(startDate.isAfter(LocalDate.now()) || startDate.isEqual(LocalDate.now())) {
            "Appointment start date must be in the present or future"
        }

        patientNote?.let {
            require(it.isNotBlank()) { "Patient note must not be blank if provided"}
        }
    }

    fun endTime(): LocalTime = startTime.plusMinutes(service.durationInMinutes.toLong())

    fun confirm() {
        require(status == AppointmentStatus.RESERVED) { "Only reserved appointments can be confirmed" }
        status = AppointmentStatus.CONFIRMED
    }

    fun cancel() {
        require(status != AppointmentStatus.CANCELLED) { "Appointment is already canceled" }
        status = AppointmentStatus.CANCELLED
    }
}
