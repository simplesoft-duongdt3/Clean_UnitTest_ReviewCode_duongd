package duongd.example.ui.executor

import duongd.example.domain.usecase.base.UseCaseExecution

class AndroidUseCaseLifoExecution : UseCaseExecution(
    AndroidTaskLifoSchedulerProvider().createScheduler(),
    AndroidPostTaskSchedulerProvider().createScheduler()
)