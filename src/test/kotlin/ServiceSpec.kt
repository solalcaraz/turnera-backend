import builders.ServiceBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ServiceTest : DescribeSpec({
    describe("Tests 0 - Service creation preconditions") {
        it("should not allow creating a service with blank name") {
            val exception = shouldThrow<IllegalArgumentException> {
                ServiceBuilder()
                    .withName("")
                    .build()
            }

            exception.message shouldBe "Service name must not be blank"
        }
        it("should not allow creating a service with non positive duration") {
            val exception = shouldThrow<IllegalArgumentException> {
                ServiceBuilder()
                    .withDuration(0)
                    .build()
            }

            exception.message shouldBe "Service duration must be greater than zero"
        }
        it("should not allow blank description when provided") {
            val exception = shouldThrow<IllegalArgumentException> {
                ServiceBuilder()
                    .withDescription("        ")
                    .build()
            }

            exception.message shouldBe "Service description must not be blanck if provided"
        }
        it("should not allow negative price") {
            val exception = shouldThrow<IllegalArgumentException> {
                ServiceBuilder()
                    .withPrice(-1.0)
                    .build()
            }

            exception.message shouldBe "Service price must not be negative or zero"
        }
        it("should allow creating a service with only required data") {
            ServiceBuilder().build()
        }
        it("should allow creating a service with description and price") {
            ServiceBuilder()
                .withDescription("General medical consultation")
                .withPrice(15000.0)
                .build()
        }
    }
})