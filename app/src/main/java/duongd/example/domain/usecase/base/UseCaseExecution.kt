package duongd.example.domain.usecase.base

import io.reactivex.Scheduler

open class UseCaseExecution constructor(val execution: Scheduler, val postExecution: Scheduler)