package duongd.example.data

import duongd.example.domain.repository.LoginRepository
import duongd.example.domain.usecase.login.LoginResultModel
import duongd.example.domain.usecase.login.RequestLoginModel
import io.reactivex.Single

class LoginRepositoryImpl : LoginRepository {
    override fun login(requestLoginModel: RequestLoginModel): Single<LoginResultModel> {
        return Single.create { e ->
            e.onSuccess(LoginResultModel(1, "Test user"))
        }
    }
}