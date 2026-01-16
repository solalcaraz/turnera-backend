package domain.appointment

import domain.Patient
import domain.Service
import java.time.LocalDate
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
            AppointmentExceptions.StartDateInPast()
        }

        patientNote?.let {
            require(it.isNotBlank()) { AppointmentExceptions.BlankPatientNote() }
        }
    }

    fun endTime(): LocalTime = startTime.plusMinutes(service.durationInMinutes.toLong())

    fun confirm() {
        require(status == AppointmentStatus.RESERVED) { AppointmentExceptions.CannotConfirmNonReservedAppointment() }
        status = AppointmentStatus.CONFIRMED
    }

    fun cancel() {
        require(status != AppointmentStatus.CANCELLED) { AppointmentExceptions.AppointmentAlreadyCancelled() }
        status = AppointmentStatus.CANCELLED
    }
}
