package nota.android.app.lifecycle

import android.content.SharedPreferences
import android.os.Parcelable
import androidx.core.content.edit
import java.io.Serializable

class SharedPreferenceLiveData<T>(key: String, defaultValue: T) :
    PersistLiveData<SharedPreferences.Editor, SharedPreferences, T>(key, defaultValue) {


    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: String
    ) {
        saver.putString(key, value)
    }

    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Boolean
    ) {
        saver.putBoolean(key, value)
    }

    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Int
    ) {
        saver.putInt(key, value)
    }

    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Long
    ) {
        saver.putLong(key, value)
    }

    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Float
    ) {
        saver.putFloat(key, value)
    }

    override fun saveValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Double
    ) {
        saver.putLong(key, value.toRawBits())
    }

    override fun saveParcelableValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Parcelable
    ) {
    }

    override fun saveSerializableValue(
        saver: SharedPreferences.Editor,
        key: String,
        value: Serializable
    ) {

    }

    override fun removeValue(saver: SharedPreferences.Editor, key: String) {
        saver.remove(key)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: String
    ): String? {
        return loader.getString(key, value)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: Boolean
    ): Boolean? {
        return loader.getBoolean(key, value)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: Int
    ): Int? {
        return loader.getInt(key, value)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: Long
    ): Long? {
        return loader.getLong(key, value)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: Float
    ): Float? {
        return loader.getFloat(key, value)
    }

    override fun loadValue(
        loader: SharedPreferences,
        key: String,
        value: Double
    ): Double? {
        return Double.fromBits(loader.getLong(key, value.toRawBits()))
    }

    override fun loadParcelableValue(
        loader: SharedPreferences,
        key: String,
        value: Parcelable
    ): T? {
        return null
    }

    override fun loadSerializableValue(
        loader: SharedPreferences,
        key: String,
        value: Serializable
    ): T? {
        return null
    }

    fun save(sp: SharedPreferences) {
        sp.edit {
            save(this)
        }
    }

    override fun save(saver: SharedPreferences.Editor) {
        super.save(saver)
        saver.apply()
    }

}