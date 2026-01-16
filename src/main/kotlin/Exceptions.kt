sealed class TimeRangeExceptions(message: String): Exception(message) {
    class InvalidTimeRange(): TimeRangeExceptions("Start time must be before end time")
}

sealed class AgendaExceptions(message: String): Exception(message) {
    class NotAvailable(): AgendaExceptions("This range of time is not available")
}

sealed class AppointmentExceptions(message: String): Exception(message) {
    class StartDateInPast(): AppointmentExceptions("Appointment start date must be in the present or future")
    class BlankPatientNote(): AppointmentExceptions("Patient note must not be blank if provided")
    class CannotConfirmNonReservedAppointment(): AppointmentExceptions("Only reserved appointments can be confirmed")
    class AppointmentAlreadyCancelled(): AppointmentExceptions("Appointment is already canceled")
}