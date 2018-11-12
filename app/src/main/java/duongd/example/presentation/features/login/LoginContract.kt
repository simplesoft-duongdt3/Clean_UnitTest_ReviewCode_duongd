package duongd.example.presentation.features.login

import com.foody.pos.base.mvp.ViewMvp
import duongd.example.presentation.base.mvp.PresenterMvp
import duongd.example.presentation.base.mvp.view.ViewSupportError
import duongd.example.presentation.base.mvp.view.ViewSupportLoading

class LoginContract {
    interface View : ViewMvp, ViewSupportLoading, ViewSupportError {
        fun showRememberMe(isRemember: Boolean)
        fun clearPassword()
        fun goToNextScreen()
    }

    abstract class Presenter : PresenterMvp<View>() {
        abstract fun onLoginClicked(userName: String, password: String)
        abstract fun onRememberMeClicked()
    }
}