package duongd.example.ui.executor

import duongd.example.domain.usecase.base.UseCaseExecution

class AndroidUseCaseExecution : UseCaseExecution(
    AndroidTaskSchedulerProvider().createScheduler(),
    AndroidPostTaskSchedulerProvider().createScheduler()
)