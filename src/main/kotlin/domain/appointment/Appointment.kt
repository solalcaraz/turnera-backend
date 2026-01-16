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
        if(startDate.isBefore(LocalDate.now())) { throw AppointmentExceptions.StartDateInPast() }

        patientNote?.let {
            if(it.isBlank()) { throw AppointmentExceptions.BlankPatientNote() }
        }
    }

    fun endTime(): LocalTime = startTime.plusMinutes(service.durationInMinutes.toLong())

    fun confirm() {
        if(status != AppointmentStatus.RESERVED) { throw AppointmentExceptions.CannotConfirm() }
        status = AppointmentStatus.CONFIRMED
    }

    fun cancel() {
        if(status == AppointmentStatus.CANCELLED) { throw AppointmentExceptions.AppointmentAlreadyCancelled() }
        status = AppointmentStatus.CANCELLED
    }
}
