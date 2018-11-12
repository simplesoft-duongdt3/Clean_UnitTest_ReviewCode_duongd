package duongd.example.domain.usecase.login

data class RequestLoginModel(
    val userName: String,
    val password: String,
    val isRememberMe: Boolean
)
