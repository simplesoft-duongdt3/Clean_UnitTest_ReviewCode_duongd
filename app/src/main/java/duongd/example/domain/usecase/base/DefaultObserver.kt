/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package duongd.example.domain.usecase.base

import io.reactivex.observers.DisposableSingleObserver

open class DefaultObserver<SuccessOutput, FailOutput>(private val failOutputFactory: (Throwable) -> FailOutput) :
    DisposableSingleObserver<SuccessOutput>() {
    private val resultListeners = mutableListOf<ResultListener<SuccessOutput, FailOutput>>()
    private val rawResultListeners = mutableListOf<RawResultListener<SuccessOutput>>()

    fun addResultListener(resultListener: ResultListener<SuccessOutput, FailOutput>) {
        resultListeners.add(resultListener)
    }

    fun addRawResultListener(rawResultListener: RawResultListener<SuccessOutput>) {
        rawResultListeners.add(rawResultListener)
    }

    override fun onSuccess(data: SuccessOutput) {
        fireSuccess(data)
    }

    override fun onError(throwable: Throwable) {
        fireFail(throwable)
    }

    private fun fireSuccess(data: SuccessOutput) {
        resultListeners.forEach { resultListener -> resultListener.success(data) }
        rawResultListeners.forEach { resultListener -> resultListener.success(data) }
    }

    private fun fireFail(throwable: Throwable) {
        val failOutput = failOutputFactory.invoke(throwable)
        resultListeners.forEach { resultListener ->
            resultListener.fail(failOutput)
        }
        rawResultListeners.forEach { resultListener ->
            resultListener.fail(throwable)
        }
    }
}
