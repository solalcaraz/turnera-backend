import builders.AppointmentBuilder
import domain.appointment.AppointmentStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class AppointmentTest : DescribeSpec({

    describe("Appointment creation rules") {
        it("should start with RESERVED status") {
            val appointment = AppointmentBuilder().build()

            appointment.status shouldBe AppointmentStatus.RESERVED
        }

        it("should not allow creating an appointment in the past") {
            assertThrows<AppointmentExceptions.StartDateInPast> {
                AppointmentBuilder()
                    .withStartDate(LocalDate.now().minusDays(1))
                    .build()
            }
        }

        it("should not allow creating an appointment with a blank patient note") {
            val exception = shouldThrow<AppointmentExceptions.BlankPatientNote>{
                AppointmentBuilder()
                    .withPatientNote("")
                    .build()
            }

            exception.message shouldBe "Patient note must not be blank if provided"
        }

        it("patient note can be null") {
            val appointment = AppointmentBuilder()
                .withPatientNote(null)
                .build()

            appointment.patientNote shouldBe null
        }
    }

    describe("Appointment state transitions") {
        it("can be confirmed from RESERVED state") {
            val appointment = AppointmentBuilder().build()
            appointment.confirm()

            appointment.status shouldBe AppointmentStatus.CONFIRMED
        }

        it("can be cancelled from RESERVED state") {
            val appointment = AppointmentBuilder().build()
            appointment.cancel()

            appointment.status shouldBe AppointmentStatus.CANCELLED
        }

        it("cannot be confirmed if appointment is cancelled") {
            val appointment = AppointmentBuilder().build()
            appointment.cancel()

            val exception = shouldThrow<AppointmentExceptions.CannotConfirm> { appointment.confirm() }

            exception.message shouldBe "Only reserved appointments can be confirmed"
        }

        it("cannot be cancelled if already cancelled") {
            val appointment = AppointmentBuilder().build()
            appointment.cancel()

            val exception = shouldThrow<AppointmentExceptions.AppointmentAlreadyCancelled> { appointment.cancel() }

            exception.message shouldBe "Appointment is already cancelled"
        }
    }
})
