package duongd.example.presentation.features.login

import com.nhaarman.mockitokotlin2.*
import duongd.example.domain.usecase.base.ResultListener
import duongd.example.domain.usecase.login.LoginFailOutput
import duongd.example.domain.usecase.login.LoginResultModel
import duongd.example.domain.usecase.login.LoginUserCase
import org.joor.Reflect.on
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class LoginPresenterTest {
    //SUT test object
    private lateinit var loginPresenter: LoginPresenter

    //DOC mock object
    private val loginUseCase: LoginUserCase = mock()
    private val view: LoginContract.View = mock()

    @BeforeEach
    fun setUp() {
        loginPresenter = LoginPresenter(loginUseCase = loginUseCase)
        loginPresenter.attachView(view = view)
    }

    @AfterEach
    fun tearDown() {

    }

    @Test
    fun `onAttachView case 01 must call`() {
        //given

        //when (attach view on setup)

        //then
        verify(view, times(numInvocations = 1)).showRememberMe(isRemember = any())
    }

    @Test
    fun `onAttachView case 02 must call with check parameter`() {
        //given

        //when (attach view on setup)

        //then
        verify(view).showRememberMe(isRemember = false)
    }

    @Test
    fun `onLoginClicked case 01`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")

        //then
        verify(view).showLoading()
    }

    @Test
    fun `onLoginClicked case 02`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")

        //then
        verify(loginUseCase).cancel()
    }

    @Test
    fun `onLoginClicked case 03 executeAsync success view hideLoading`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            val successResult = LoginResultModel(1, "fake result user")
            firstValue.success(successResult)
        }

        //then
        verify(view).hideLoading()
    }

    @Test
    fun `onLoginClicked case 04 executeAsync success view hideError`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            val successResult = LoginResultModel(1, "fake result user")
            firstValue.success(successResult)
        }

        //then
        verify(view).hideError()
    }

    @Test
    fun `onLoginClicked case 05 executeAsync success view goToNextScreen`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            val successResult = LoginResultModel(1, "fake result user")
            firstValue.success(successResult)
        }

        //then
        verify(view).goToNextScreen()
    }

    @ParameterizedTest
    @ArgumentsSource(LoginFailOutputArgumentsProvider::class)
    fun `onLoginClicked case 06 executeAsync fail view hideLoading`(loginFailOutput: LoginFailOutput) {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            firstValue.fail(loginFailOutput)
        }

        //then
        verify(view).hideLoading()
    }

    @Test
    fun `onLoginClicked case 07 executeAsync fail view LoginFailType COMMON`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            firstValue.fail(LoginFailOutput(LoginFailOutput.LoginFailType.COMMON))
        }

        //then
        verify(view).showError("COMMON")
    }

    @Test
    fun `onLoginClicked case 08 executeAsync fail view LoginFailType WRONG_USERNAME_PASSWORD`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            firstValue.fail(LoginFailOutput(LoginFailOutput.LoginFailType.WRONG_USERNAME_PASSWORD))
        }

        //then
        verify(view).clearPassword()
        verify(view).showError("WRONG_USERNAME_PASSWORD")
    }

    @Test
    fun `onLoginClicked case 09 executeAsync fail view LoginFailType NETWORK_ERROR`() {
        //given

        //when
        loginPresenter.onLoginClicked(userName = "fake user", password = "fake password")
        argumentCaptor<ResultListener<LoginResultModel, LoginFailOutput>>().apply {
            verify(loginUseCase).executeAsync(resultListener = capture(), input = any())
            firstValue.fail(LoginFailOutput(LoginFailOutput.LoginFailType.NETWORK_ERROR))
        }

        //then
        verify(view).showError("NETWORK_ERROR")
    }

    @Test
    fun `onRememberMeClicked case 01 isRememberMeBefore true`() {
        //given
        val isRememberMeBefore = true
        on(loginPresenter).set("isRememberMe", isRememberMeBefore)

        //when
        loginPresenter.onRememberMeClicked()

        //then
        val isRememberMeAfter: Boolean = on(loginPresenter).get("isRememberMe")
        Assertions.assertEquals(!isRememberMeBefore, isRememberMeAfter)
    }

    @Test
    fun `onRememberMeClicked case 02 isRememberMeBefore false`() {
        //given
        val isRememberMeBefore = false
        on(loginPresenter).set("isRememberMe", isRememberMeBefore)

        //when
        loginPresenter.onRememberMeClicked()

        //then
        val isRememberMeAfter: Boolean = on(loginPresenter).get("isRememberMe")
        Assertions.assertEquals(!isRememberMeBefore, isRememberMeAfter)
    }

    @ParameterizedTest
    @ArgumentsSource(BooleanArgumentsProvider::class)
    fun `onRememberMeClicked case 01 - 02 isRememberMeBefore true + false`(isRememberMeBefore: Boolean) {
        //given
        on(loginPresenter).set("isRememberMe", isRememberMeBefore)

        //when
        loginPresenter.onRememberMeClicked()

        //then
        val isRememberMeAfter: Boolean = on(loginPresenter).get("isRememberMe")
        Assertions.assertEquals(!isRememberMeBefore, isRememberMeAfter)
    }

    @ParameterizedTest
    @ArgumentsSource(BooleanArgumentsProvider::class)
    fun `onRememberMeClicked case 03 view showRememberMe`(isRememberMeBefore: Boolean) {
        //given
        val isRememberMeAfter = !isRememberMeBefore
        //when
        loginPresenter.onRememberMeClicked()

        //then
        verify(view).showRememberMe(isRememberMeAfter)
    }

    class BooleanArgumentsProvider: ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(true, false).map { Arguments.of(it) }
        }
    }

    class LoginFailOutputArgumentsProvider: ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                LoginFailOutput(LoginFailOutput.LoginFailType.COMMON),
                LoginFailOutput(LoginFailOutput.LoginFailType.WRONG_USERNAME_PASSWORD),
                LoginFailOutput(LoginFailOutput.LoginFailType.NETWORK_ERROR)
            ).map { Arguments.of(it) }
        }
    }

    @Test
    fun `onDetachView `() {
        //given

        //when
        loginPresenter.onDetachView()

        //then
        verify(loginUseCase).cancel()
    }


}