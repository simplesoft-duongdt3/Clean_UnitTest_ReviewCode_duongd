package duongd.example.domain.usecase.base

interface ResultListener<in SuccessOutput, in FailOutput> {
    fun success(successOutput: SuccessOutput)
    fun fail(failOutput: FailOutput)
}