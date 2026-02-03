package nota.android.os;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class WeakReferenceHandler<T> extends Handler {

    private WeakReference<T> mReference;

    public WeakReferenceHandler(T reference) {
        mReference = new WeakReference<>(reference);
    }

    public void cancel() {
        mReference.clear();
        mReference = null;
        removeCallbacksAndMessages(null);
    }

    @Override
    public final void handleMessage(Message msg) {
        if (mReference == null) {
            return;
        }
        T obj = mReference.get();
        if (obj == null) {
            return;
        }
        handleMessage(obj, msg);
    }

    protected abstract void handleMessage(T reference, Message msg);
}