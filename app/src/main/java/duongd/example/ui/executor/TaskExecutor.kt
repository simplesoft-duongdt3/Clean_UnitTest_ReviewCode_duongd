package duongd.example.ui.executor

import com.foody.pos.base.executor.ThreadExecutor
import java.util.concurrent.*

class TaskExecutor internal constructor(val type: Type) : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor

    enum class Type {
        FIFO, LIFO
    }

    init {
        val queueLifo = LinkedBlockingDeque<Runnable>()
        val linkedBlockingQueue = LinkedBlockingQueue<Runnable>()
        val workQueue: BlockingQueue<Runnable> = if (type == Type.FIFO) linkedBlockingQueue else queueLifo
        val jobThreadFactory = JobThreadFactory()
        this.threadPoolExecutor = ThreadPoolExecutor(10, 100, 20L, TimeUnit.SECONDS, workQueue, jobThreadFactory)
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            val threadName = "TaskExecutor_${counter++}"
            return Thread(runnable, threadName)
        }
    }

    companion object {
        @JvmStatic
        private val TASK_EXECUTOR: TaskExecutor by lazy {
            new(Type.FIFO)
        }

        @JvmStatic
        fun get(): TaskExecutor = TASK_EXECUTOR

        @JvmStatic
        private val TASK_EXECUTOR_LIFO: TaskExecutor by lazy {
            new(Type.LIFO)
        }

        @JvmStatic
        fun getLifo(): TaskExecutor = TASK_EXECUTOR_LIFO

        @JvmStatic
        fun new(type: Type): TaskExecutor = TaskExecutor(type)
    }
}
