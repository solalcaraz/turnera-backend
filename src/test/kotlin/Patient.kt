import builders.PatientBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PatientTest : DescribeSpec({

    describe("Precondition validations when creating a Patient") {

        it("should not allow creating a patient with blank first name") {
            val exception = shouldThrow<IllegalArgumentException> {
                PatientBuilder()
                    .withFirstName("")
                    .build()
            }

            exception.message shouldBe "Patient first name must not be blank"
        }

        it("should not allow creating a patient with blank last name") {
            val exception = shouldThrow<IllegalArgumentException> {
                PatientBuilder()
                    .withLastName("")
                    .build()
            }

            exception.message shouldBe "Patient last name must not be blank"
        }

        it("should not allow creating a patient without contact information") {
            val exception = shouldThrow<IllegalArgumentException> {
                PatientBuilder()
                    .withoutContactInformation()
                    .build()
            }

            exception.message shouldBe "Patient must have at least one contact method (email or phone)"
        }

        it("should allow creating a patient with email only") {
            PatientBuilder().build()
        }

        it("should allow creating a patient with phone only") {
            PatientBuilder()
                .withEmail("")
                .withPhone("11", "12345678")
                .build()
        }
    }
})
