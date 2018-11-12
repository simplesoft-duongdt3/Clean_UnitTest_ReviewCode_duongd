package duongd.example.domain.usecase.base

class ExecuteResultListener<SuccessOutput, FailOutput> : ResultListener<SuccessOutput, FailOutput> {
    var isSuccess = false
    var successOutput: SuccessOutput? = null
    var failOutput: FailOutput? = null
    override fun success(successOutput: SuccessOutput) {
        isSuccess = true
        this.successOutput = successOutput
    }

    override fun fail(failOutput: FailOutput) {
        isSuccess = false
        this.failOutput = failOutput
    }
}