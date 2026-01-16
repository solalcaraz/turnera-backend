package builders

import domain.Patient
import domain.Service
import domain.appointment.Appointment
import java.time.LocalDate
import java.time.LocalTime

public class AppointmentBuilder {
    private var service = ServiceBuilder().build()
    private var patient = PatientBuilder().build()
    private var startDate = LocalDate.now().plusDays(1)
    private var startTime = LocalTime.now()
    private var patientNote: String? = null

    fun withService(service: Service) = apply { this.service = service }
    fun withPatient(patient: Patient) = apply { this.patient = patient }
    fun withStartDate(date: LocalDate) = apply { this.startDate = date }
    fun withStartTime(time: LocalTime) = apply { this.startTime = time }
    fun withPatientNote(note: String?) = apply { this.patientNote = note }

    fun build(): Appointment = Appointment(service, patient, startDate, startTime, patientNote)
}