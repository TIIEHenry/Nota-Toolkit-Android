package nota.android.data.parcel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParcelableSerializer {
    private static final String TAG = "ParcelableSerializer";
    private static final HashMap<String, Parcelable.Creator<?>> mCreators = new HashMap<>();

    public static byte[] serialize(@NonNull Parcelable value) {
        Parcel source = Parcel.obtain();
        try {
            value.writeToParcel(source, 0);
            return source.marshall();
        } finally {
            source.recycle();
        }
    }

    @Nullable
    public static <T extends Parcelable> T deserialize(byte[] bytes, Class<T> tClass, @Nullable T defaultValue) {
        if (tClass == null) {
            return defaultValue;
        } else {
            if (bytes == null) {
                return defaultValue;
            } else {
                Parcel source = Parcel.obtain();
                source.unmarshall(bytes, 0, bytes.length);
                source.setDataPosition(0);

                try {
                    String name = tClass.toString();
                    Parcelable.Creator<T> creator;
                    synchronized (mCreators) {
                        creator = (Parcelable.Creator) mCreators.get(name);
                        if (creator == null) {
                            Field f = tClass.getField("CREATOR");
                            creator = (Parcelable.Creator) f.get(null);
                            if (creator != null) {
                                mCreators.put(name, creator);
                            }
                        }
                    }

                    if (creator == null) {
                        throw new Exception("Parcelable protocol requires a non-null static Parcelable.Creator object called CREATOR on class " + name);
                    }

                    return (T) creator.createFromParcel(source);
                } catch (Exception e) {
                    return defaultValue;
                } finally {
                    source.recycle();
                }
            }
        }
    }


    /**
     * 将Parcelable列表序列化为字节数组
     */
    public static byte[] serializeList(List<? extends Parcelable> parcelables) {
        Parcel parcel = Parcel.obtain();
        try {
            parcel.writeList(parcelables);
            return parcel.marshall();
        } finally {
            parcel.recycle();
        }
    }

    /**
     * 从字节数组反序列化Parcelable列表
     */
    @SuppressWarnings("unchecked")
    public static <T extends Parcelable> List<T> deserializeList(byte[] bytes, Class<T> tClass) {
        if (bytes == null || bytes.length == 0) {
            return new ArrayList<>();
        }

        Parcel parcel = Parcel.obtain();
        try {
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            List<T> list = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                parcel.readList(list, tClass.getClassLoader(), tClass);
            } else {
                parcel.readList(list, tClass.getClassLoader());
            }
            return list;
        } finally {
            parcel.recycle();
        }
    }
}
