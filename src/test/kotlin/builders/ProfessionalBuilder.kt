package builders

import domain.professional.ContactLink
import domain.professional.ProfessionalType
import domain.Service
import domain.Agenda
import domain.professional.Professional

public class ProfessionalBuilder {
    private var name: String = "Mike Fly"
    private var email: String = "mike.fly@email.com"
    private var professionalType: ProfessionalType = ProfessionalType.PSYCHOLOGIST
    private var services = setOf(ServiceBuilder().build())
    private var agendas = setOf(AgendaBuilder().build())
    private var imageUrl: String? = null
    private var contactLinks: Set<ContactLink> = emptySet()

    fun withName(name: String) = apply { this.name = name }
    fun withEmail(email: String) = apply { this.email = email }
    fun withProfessionalType(type: ProfessionalType) = apply { this.professionalType = type }
    fun withServices(services: Set<Service>) = apply { this.services = services }
    fun withAgendas(agendas: Set<Agenda>) = apply { this.agendas = agendas }
    fun withImageUrl(imageUrl: String?) = apply { this.imageUrl = imageUrl }
    fun withContactLinks(links: Set<ContactLink>) = apply { this.contactLinks = links }

    fun build(): Professional =
        Professional(name, email, professionalType, services, agendas, imageUrl, contactLinks)
}
