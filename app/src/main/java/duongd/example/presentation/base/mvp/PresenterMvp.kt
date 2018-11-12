package duongd.example.presentation.base.mvp

import com.foody.pos.base.mvp.ViewMvp
import org.koin.standalone.KoinComponent
import java.lang.ref.WeakReference

abstract class PresenterMvp<V : ViewMvp> : KoinComponent {
    private var weakReference: WeakReference<V>? = null
    open fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
        }
        onAttachView()
    }

    protected open fun onAttachView() {

    }

    fun detachView() {
        weakReference?.clear()
        weakReference = null
        onDetachView()
    }

    open fun onDetachView() {

    }


    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
}