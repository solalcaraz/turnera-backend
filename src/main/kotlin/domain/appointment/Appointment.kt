package domain.appointment

import domain.Patient
import domain.Service
import java.time.LocalDateTime

class Appointment(
    val service: Service,
    val patient: Patient,
    val startDateTime: LocalDateTime,
    val patientNote: String? = null
){
    val status: AppointmentStatus = AppointmentStatus.RESERVED

    init {
        require(startDateTime.isAfter(LocalDateTime.now()) || startDateTime.isEqual(LocalDateTime.now())) {
            "Appointment start date and time must be in the present or future"
        }

        patientNote?.let {
            require(it.isNotBlank()) { "Patient note must not be blank if provided"}
        }
    }
}