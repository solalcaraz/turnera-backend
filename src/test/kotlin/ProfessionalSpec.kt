import builders.ProfessionalBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ProfessionalTest : DescribeSpec({

    describe("Precondition validations when creating a Professional") {

        it("should not allow creating a professional with blank name") {
            val exception = shouldThrow<IllegalArgumentException> {
                ProfessionalBuilder()
                    .withName("")
                    .build()
            }

            exception.message shouldBe "Professional name must not be blank"
        }

        it("should not allow creating a professional with blank email") {
            val exception = shouldThrow<IllegalArgumentException> {
                ProfessionalBuilder()
                    .withEmail("")
                    .build()
            }

            exception.message shouldBe "Professional email must not be blank"
        }

        it("should not allow creating a professional without services") {
            val exception = shouldThrow<IllegalArgumentException> {
                ProfessionalBuilder()
                    .withServices(emptySet())
                    .build()
            }

            exception.message shouldBe "Professional must offer at least one service"
        }

        it("should not allow creating a professional without agendas") {
            val exception = shouldThrow<IllegalArgumentException> {
                ProfessionalBuilder()
                    .withAgendas(emptySet())
                    .build()
            }

            exception.message shouldBe "Professional must have at least one agenda"
        }

        it("should allow creating a professional with valid data") {
            val professional = ProfessionalBuilder().build()

            professional.name shouldBe "Mike Fly"
            professional.email shouldBe "mike.fly@email.com"
        }
    }
})
