import builders.AppointmentBuilder
import builders.ServiceBuilder
import domain.Agenda
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class AgendaTest : DescribeSpec({

    describe("Agenda appointment scheduling") {

        it("should allow adding a non-overlapping appointment") {
            val agenda = Agenda("Default")
            val appointment = AppointmentBuilder().build()

            agenda.addAppointment(appointment)

            agenda.isAvailable(appointment) shouldBe false
        }

        it("should not allow adding overlapping appointments") {
            val agenda = Agenda("Default")
            val service = ServiceBuilder()
                .withDuration(60)
                .build()

            val firstAppointment = AppointmentBuilder()
                .withService(service)
                .withStartDateTime(LocalDateTime.now().plusDays(1).withHour(10))
                .build()

            val overlappingAppointment = AppointmentBuilder()
                .withService(service)
                .withStartDateTime(firstAppointment.startDateTime.plusMinutes(30))
                .build()

            agenda.addAppointment(firstAppointment)

            val exception = shouldThrow<IllegalArgumentException> {
                agenda.addAppointment(overlappingAppointment)
            }

            exception.message shouldBe "Appointment time slot is not available"
        }
    }
})
