package duongd.example.domain.usecase.base

import io.reactivex.disposables.Disposable

class UseCaseTask(var disposable: Disposable) {

    fun cancel() {
        disposable.dispose()
    }
}