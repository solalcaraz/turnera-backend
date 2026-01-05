package builders

import domain.Agenda
import domain.appointment.Appointment

class AgendaBuilder {

    private var name: String = "Default Agenda"
    private var appointments: MutableList<Appointment> = mutableListOf()

    fun withName(name: String) = apply { this.name = name }
    fun withAppointments(appointments: List<Appointment>) = apply { this.appointments = appointments.toMutableList() }

    fun build(): Agenda {
        val agenda = Agenda(name)
        appointments.forEach { agenda.addAppointment(it) }
        return agenda
    }
}
