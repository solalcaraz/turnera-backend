package domain.professional

import domain.Agenda
import domain.Service

class Professional(
    val name: String,
    val email: String,
    val professionalType: ProfessionalType,
    val services: Set<Service>,
    val agendas: Set<Agenda>,
    val imageUrl: String? = null,
    val contactLinks: Set<ContactLink> = emptySet()
){
    init {
        require(name.isNotBlank()) { "Professional name must not be blank" }
        require(email.isNotBlank()) { "Professional email must not be blank" }
        require(services.isNotEmpty()) { "Professional must offer at least one service" }
        require(agendas.isNotEmpty()) { "Professional must have at least one agenda" }
    }
}