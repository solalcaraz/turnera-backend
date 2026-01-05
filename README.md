# turnera-backend [![build](https://github.com/solalcaraz/turnera-backend/actions/workflows/build.yml/badge.svg)](https://github.com/solalcaraz/turnera-backend/actions/workflows/build.yml)

## Entrega 0 – Initial Domain Model

This release includes the first version of the domain model for the appointment system. It defines the main entities, their relationships, and basic business rules.

### Entities
- **Service:** Defines services offered and their duration.
- **Patient:** Represents patients making reservations, ensuring at least one contact method.
- **Appointment:** Represents reservations with a Service and a Patient; starts with RESERVED status and optional patient note.
- **Agenda:** Manages appointments and ensures no overlapping reservations.
- **Professional:** Represents a professional with services and agendas.
- **ContactLink & ProfessionalType:** Supporting models for professional contact and type.

### Builders
- Builders are implemented for testing purposes: `ServiceBuilder`, `PatientBuilder`, `AppointmentBuilder`, `AgendaBuilder`, `ProfessionalBuilder`.

### Rules implemented
- Required field validation for all entities.
- Appointments cannot be created in the past.
- Agenda prevents overlapping appointments.
- Patient notes, if provided, cannot be blank.

### Pending enhancements
- Service availability days and personalized schedules.
- Agenda modalities (virtual, in-person, group/individual).
- Blocked time slots, buffer times, and cancellation policies.
- Enforcement of service-specific restrictions.

-------------------
## Tags
- entrega-0-back → Initial domain model implementation
