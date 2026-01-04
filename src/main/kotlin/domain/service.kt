package domain.service

class Service(
    val name: String,
    val description: String?,
    val durationInMinutes: Int,
    val price: Double? = 0
){
    // validaciones previas al inicializar un service
    init {
        requiere(name.isNotBlank()) { "Service name must not be blank" }
        requiere(durationInMinutes > 0) { "Service duration must be greater than zero" }
        description?.let {
            requiere(it.isNotBlank()) { "Service description must not be blanck if provided" }
        }
        price?.let {
            require(it >= 0) { "Service price must not be negative or zero" }
        }
    }
}