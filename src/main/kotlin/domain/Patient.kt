package domain

class Patient(
    val firstName: String,
    val lastName: String,
    val email: String?,
    val areaCode: String?,
    val phoneNumber: String?
){
    init {
        require(firstName.isNotBlank()) { "Patient first name must not be blank" }
        require(lastName.isNotBlank()) { "Patient last name must not be blank" }

        val hasEmail = !email.isNullOrBlank()
        val hasPhone = !areaCode.isNullOrBlank() && !phoneNumber.isNullOrBlank()

        require(hasEmail || hasPhone) { "Patient must have at least one contact method (email or phone)" }
    }
}