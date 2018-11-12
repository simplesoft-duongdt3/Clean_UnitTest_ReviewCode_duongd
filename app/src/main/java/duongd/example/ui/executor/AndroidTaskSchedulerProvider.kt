package duongd.example.ui.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class AndroidTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = Schedulers.from(TaskExecutor.get())
}