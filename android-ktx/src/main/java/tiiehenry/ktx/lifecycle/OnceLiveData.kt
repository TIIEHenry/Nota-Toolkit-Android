package tiiehenry.ktx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Description: 只执行一次的LiveData
 */
class OnceLiveData<T> : MutableLiveData<T>() {

    private var isRead: AtomicBoolean = AtomicBoolean(false)

    /**
     * ensure the event is non-null and can only been seen once
     */

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer {
            if (isRead.compareAndSet(false, true)) {
                observer.onChanged(it)
            }
        })
    }
    override fun postValue(value: T) {
        isRead.set(false)
        super.postValue(value)
    }

    override fun setValue(value: T) {
        isRead.set(false)
        super.setValue(value)
    }
}