package duongd.example.presentation.features.login

import duongd.example.domain.usecase.base.ResultListener
import duongd.example.domain.usecase.login.LoginFailOutput
import duongd.example.domain.usecase.login.LoginResultModel
import duongd.example.domain.usecase.login.LoginUserCase
import duongd.example.domain.usecase.login.RequestLoginModel

class LoginPresenter(
    private val loginUserCase: LoginUserCase
) : LoginContract.Presenter() {

    private var isRememberMe = false
    override fun onAttachView() {
        super.onAttachView()
        view?.showRememberMe(isRememberMe)
    }

    override fun onLoginClicked(userName: String, password: String) {
        view?.showLoading()
        loginUserCase.cancel()
        val requestLoginModel = RequestLoginModel(userName = userName, password = password, isRememberMe = isRememberMe)

        loginUserCase.executeAsync(
            resultListener = object : ResultListener<LoginResultModel, LoginFailOutput> {
                override fun success(successOutput: LoginResultModel) {
                    view?.hideLoading()
                    view?.hideError()
                    view?.goToNextScreen()
                }

                override fun fail(failOutput: LoginFailOutput) {
                    view?.hideLoading()
                    when (failOutput.loginFailType) {
                        LoginFailOutput.LoginFailType.COMMON -> view?.showError("COMMON")
                        LoginFailOutput.LoginFailType.WRONG_USERNAME_PASSWORD -> {
                            view?.clearPassword()
                            view?.showError("WRONG_USERNAME_PASSWORD")
                        }
                        LoginFailOutput.LoginFailType.NETWORK_ERROR -> view?.showError("NETWORK_ERROR")
                    }
                }
            },
            input = requestLoginModel
        )
    }

    override fun onRememberMeClicked() {
        isRememberMe = !isRememberMe
        view?.showRememberMe(isRememberMe)
    }

    override fun onDetachView() {
        super.onDetachView()
        loginUserCase.cancel()
    }

}