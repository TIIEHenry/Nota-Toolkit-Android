package nota.android.app.lifecycle

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import java.io.Serializable

abstract class PersistLiveData<Saver, Loader, T>(val key: String, val defaultValue: T) :
    MutableLiveData<T>(defaultValue) {
    abstract fun saveValue(saver: Saver, key: String, value: String)
    abstract fun saveValue(saver: Saver, key: String, value: Boolean)
    abstract fun saveValue(saver: Saver, key: String, value: Int)
    abstract fun saveValue(saver: Saver, key: String, value: Long)
    abstract fun saveValue(saver: Saver, key: String, value: Float)
    abstract fun saveValue(saver: Saver, key: String, value: Double)
    abstract fun saveParcelableValue(saver: Saver, key: String, value: Parcelable)
    abstract fun saveSerializableValue(saver: Saver, key: String, value: Serializable)
    abstract fun removeValue(saver: Saver, key: String)
    abstract fun loadValue(loader: Loader, key: String, value: String): String?
    abstract fun loadValue(loader: Loader, key: String, value: Boolean): Boolean?
    abstract fun loadValue(loader: Loader, key: String, value: Int): Int?
    abstract fun loadValue(loader: Loader, key: String, value: Long): Long?
    abstract fun loadValue(loader: Loader, key: String, value: Float): Float?
    abstract fun loadValue(loader: Loader, key: String, value: Double): Double?
    abstract fun loadParcelableValue(loader: Loader, key: String, value: Parcelable): T?
    abstract fun loadSerializableValue(loader: Loader, key: String, value: Serializable): T?

    open fun save(saver: Saver) {
        val v = value
        if (v != null) {
            when (v) {
                is String -> {
                    saveValue(saver, key, v)
                }

                is Int -> {
                    saveValue(saver, key, v)
                }

                is Boolean -> {
                    saveValue(saver, key, v)
                }

                is Float -> {
                    saveValue(saver, key, v)
                }

                is Long -> {
                    saveValue(saver, key, v)
                }

                is Double -> {
                    saveValue(saver, key, v)
                }

                is Parcelable -> {
                    saveParcelableValue(saver, key, v)
                }

                is Serializable -> {
                    saveSerializableValue(saver, key, v)
                }

                else -> {
                    // 处理其他类型
                }
            }
        } else {
            removeValue(saver, key)
        }
    }

    open fun load(
        loader: Loader,
        post: Boolean = true
    ): T? {
        val v = when (defaultValue) {
            is String -> {
                loadValue(loader, key, defaultValue)
            }

            is Int -> {
                loadValue(loader, key, defaultValue)
            }

            is Boolean -> {
                loadValue(loader, key, defaultValue)
            }

            is Float -> {
                loadValue(loader, key, defaultValue)
            }

            is Long -> {
                loadValue(loader, key, defaultValue)
            }

            is Double -> {
                loadValue(loader, key, defaultValue)
            }

            is Parcelable -> {
                loadParcelableValue(loader, key, defaultValue)
            }

            else -> {
                defaultValue
            }
        }
        if (post) {
            postValue(v as T?)
        } else {
            value = v as T?
        }
        return v
    }
}