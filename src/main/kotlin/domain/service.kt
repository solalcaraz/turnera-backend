package domain

class Service(
    val name: String,
    val description: String?,
    val durationInMinutes: Int,
    val price: Double? = 0.0
){
    // Precondition validations when creating a Service
    init {
        require(name.isNotBlank()) { "Service name must not be blank" }
        require(durationInMinutes > 0) { "Service duration must be greater than zero" }
        description?.let {
            require(it.isNotBlank()) { "Service description must not be blanck if provided" }
        }
        price?.let {
            require(it >= 0) { "Service price must not be negative or zero" }
        }
    }
}