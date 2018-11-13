package duongd.example.di

import duongd.example.data.LoginRepositoryImpl
import duongd.example.domain.repository.LoginRepository
import duongd.example.domain.usecase.base.UseCaseExecution
import duongd.example.domain.usecase.login.LoginUserCase
import duongd.example.presentation.features.login.LoginContract
import duongd.example.presentation.features.login.LoginPresenter
import duongd.example.ui.executor.AndroidUseCaseExecution
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object KoinModules {
    fun modules(): List<Module> {
        return listOf(
            loginModule
        )
    }

    private val loginModule = module {
        factory {
            LoginPresenter(
                loginUseCase = get()
            ) as LoginContract.Presenter
        }

        factory {
            LoginUserCase(
                loginRepository = get(),
                useCaseExecution = get()
            )
        }

        single {
            LoginRepositoryImpl() as LoginRepository
        }

        single {
            AndroidUseCaseExecution() as UseCaseExecution
        }

    }
}