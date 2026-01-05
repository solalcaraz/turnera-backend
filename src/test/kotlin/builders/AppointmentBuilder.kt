package builders

import domain.appointment.Appointment
import java.time.LocalDateTime

public class AppointmentBuilder {
    private var service = ServiceBuilder().build()
    private var patient = PatientBuilder().build()
    private var startDateTime = LocalDateTime.now().plusDays(1)
    private var patientNote: String? = null

    fun withStartDateTime(dateTime: LocalDateTime) = apply { this.startDateTime = dateTime }
    fun withPatientNote(note: String) = apply { this.patientNote = note }

    fun build(): Appointment = Appointment(service, patient, startDateTime, patientNote)
}
