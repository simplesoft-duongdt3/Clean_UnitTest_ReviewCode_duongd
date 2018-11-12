package duongd.example.ui.executor

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun createScheduler(): Scheduler
}