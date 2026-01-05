import builders.AppointmentBuilder
import domain.appointment.AppointmentStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class AppointmentTest : DescribeSpec({

    describe("Tests 0 - Precondition validations when creating an Appointment") {
        it("should not allow creating an appointment in the past") {
            val exception = shouldThrow<IllegalArgumentException> {
                AppointmentBuilder()
                    .withStartDateTime(LocalDateTime.now().minusDays(1))
                    .build()
            }

            exception.message shouldBe "Appointment start date and time must be in the present or future"
        }

        it("should not allow creating an appointment with a blank patient note") {
            val exception = shouldThrow<IllegalArgumentException>{
                AppointmentBuilder()
                    .withPatientNote("")
                    .build()
            }

            exception.message shouldBe "Patient note must not be blank if provided"
        }

        it("should start with RESERVED status") {
            val appointment = AppointmentBuilder().build()

            appointment.status shouldBe AppointmentStatus.RESERVED
        }
    }
})
