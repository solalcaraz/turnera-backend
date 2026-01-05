package builders

import domain.Patient

class PatientBuilder {
    private var firstName: String = "John"
    private var lastName: String = "Doe"
    private var email: String? = "john@mail.com"
    private var areaCode: String? = null
    private var phoneNumber: String? = null

    fun withFirstName(name: String) = apply { this.firstName = name }
    fun withLastName(lastName: String) = apply { this.lastName = lastName }
    fun withEmail(email: String?) = apply { this.email = email }
    fun withPhone(areaCode: String, phoneNumber: String) = apply {
        this.areaCode = areaCode
        this.phoneNumber = phoneNumber
    }
    fun withoutContactInformation() = apply {
        this.email = null
        this.areaCode = null
        this.phoneNumber = null
    }
    fun build() = Patient(firstName, lastName, email, areaCode, phoneNumber )
}