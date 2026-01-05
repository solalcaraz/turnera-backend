package domain.professional

enum class ContactType {
    WEBSITE,
    INSTAGRAM,
    WHATSAPP,
    EMAIL,
    OTHER
}

data class ContactLink(
    val type: ContactType,
    val url: String
) {
    init {
        require(url.isNotBlank()) { "Contact link url must not be blank" }
    }
}
