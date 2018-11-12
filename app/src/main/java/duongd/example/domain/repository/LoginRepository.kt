package duongd.example.domain.repository

import duongd.example.domain.usecase.login.LoginResultModel
import duongd.example.domain.usecase.login.RequestLoginModel
import io.reactivex.Single

interface LoginRepository {
    fun login(requestLoginModel: RequestLoginModel): Single<LoginResultModel>
}