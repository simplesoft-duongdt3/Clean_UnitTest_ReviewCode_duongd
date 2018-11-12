package duongd.example.ui.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidPostTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = AndroidSchedulers.mainThread()
}