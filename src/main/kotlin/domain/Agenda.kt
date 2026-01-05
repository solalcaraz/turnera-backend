package domain

import domain.appointment.Appointment

class Agenda(val name: String) {
    private val appointments = mutableListOf<Appointment>()

    fun addAppointment(appointment: Appointment) {
        require(isAvailable(appointment)) { "Appointment time slot is not available" }
        appointments.add(appointment)
    }
    fun isAvailable(newAppointment: Appointment): Boolean =
        appointments.none { existing -> overlaps(existing, newAppointment) }

    private fun overlaps(a: Appointment, b: Appointment): Boolean {
        val aStart = a.startDateTime
        val aEnd = a.startDateTime.plusMinutes(a.service.durationInMinutes.toLong())

        val bStart = b.startDateTime
        val bEnd = b.startDateTime.plusMinutes(b.service.durationInMinutes.toLong())

        return aStart < bEnd && bStart < aEnd
    }
}
