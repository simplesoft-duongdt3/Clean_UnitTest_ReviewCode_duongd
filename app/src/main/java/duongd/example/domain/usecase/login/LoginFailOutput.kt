package duongd.example.domain.usecase.login

data class LoginFailOutput(
    val loginFailType: LoginFailType
) {
    enum class LoginFailType {
        COMMON,
        WRONG_USERNAME_PASSWORD,
        NETWORK_ERROR
    }
}
