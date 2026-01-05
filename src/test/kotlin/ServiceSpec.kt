package kotlin

import domain.Service
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class ServiceTest : DescribeSpec({
    describe("Tests 0 - Precondition validations when creating a Service") {
        it("should not allow creating a service with blank name") {
            shouldThrow<IllegalArgumentException> {
                Service(name = "", durationInMinutes = 30)
            }
        }
        it("should not allow creating a service with non positive duration") {
            shouldThrow<IllegalArgumentException> {
                Service(name = "Consultation", durationInMinutes = 0)
            }
        }
        it("should not allow blank description when provided") {
            shouldThrow<IllegalArgumentException> {
                Service(name = "Consultation", durationInMinutes = 30, description = "   ")
            }
        }
        it("should not allow negative price") {
            shouldThrow<IllegalArgumentException> {
                Service(name = "Consultation", durationInMinutes = 30, price = -1.0)
            }
        }
        it("should allow creating a service with only required data") {
            Service(name = "Consultation", durationInMinutes = 30)
        }
        it("should allow creating a service with description and price") {
            Service(
                name = "Consultation", durationInMinutes = 45,
                description = "General medical consultation", price = 15000.0
            )
        }
    }
})