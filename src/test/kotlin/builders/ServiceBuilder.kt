package builders

import domain.Service

class ServiceBuilder {
    private var name = "Consultation"
    private var durationInMinutes = 30
    private var description: String? = null
    private var price: Double? = null

    fun withName(name: String) = apply { this.name = name }
    fun withDuration(minutes: Int) = apply { this.durationInMinutes = minutes }
    fun withDescription(description: String?) = apply { this.description = description }
    fun withPrice(price: Double?) = apply { this.price = price }

    fun build() = Service(name = name, durationInMinutes = durationInMinutes, description = description, price = price)
}