package duongd.example.domain.usecase.login

import duongd.example.domain.repository.LoginRepository
import duongd.example.domain.usecase.base.UseCase
import duongd.example.domain.usecase.base.UseCaseExecution
import duongd.example.domain.usecase.login.exception.LoginNetworkFailException
import duongd.example.domain.usecase.login.exception.LoginWrongUserNamePasswordException
import io.reactivex.Single

class LoginUserCase(
    private val loginRepository: LoginRepository,
    useCaseExecution: UseCaseExecution
) :
    UseCase<RequestLoginModel, LoginResultModel, LoginFailOutput>(useCaseExecution) {
    override fun buildUseCaseObservable(input: RequestLoginModel): Single<LoginResultModel> {
        return loginRepository.login(input)
    }

    override fun createFailOutput(throwable: Throwable): LoginFailOutput {
        return when (throwable) {
            is LoginNetworkFailException -> LoginFailOutput(LoginFailOutput.LoginFailType.NETWORK_ERROR)
            is LoginWrongUserNamePasswordException -> LoginFailOutput(LoginFailOutput.LoginFailType.WRONG_USERNAME_PASSWORD)
            else -> LoginFailOutput(LoginFailOutput.LoginFailType.COMMON)
        }
    }
}