package nota.android.log;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Set;

/**
 * Logger
 * 小写受到控制，大写强制输出
 * 单独throwable时，只有exception打印堆栈
 */
public class Logger {
    private static final String TAG = "Logger";
    private static boolean globalSwitch = true;

    public final String tag;
    private boolean enabled;

    /**
     * 忽略全局开关
     */
    public boolean ignoreGlobal = false;

    /**
     * 在Release应用中打印debug信息
     */
    public boolean alwaysPrintDebug = false;

    public Logger(String tag, boolean enabled) {
        this.tag = tag;
        this.enabled = enabled;
    }

    @NonNull
    public static Logger getLogger(String tag) {
        return getLogger(tag, true);
    }

    @NonNull
    public static Logger getLogger(String tag, boolean enabled) {
        return new Logger(tag, enabled);
    }

    public boolean isEnabled() {
        if (ignoreGlobal) {
            return enabled;
        }
        return globalSwitch && enabled;
    }

    public Logger setEnabled(boolean enabled) {
        if (enabled) {
            enable();
        } else {
            disable();
        }
        return this;
    }

    public Logger enable() {
        this.enabled = true;
        return this;
    }

    public Logger disable() {
        enabled = false;
        return this;
    }

    public void v(String msg) {
        if (isEnabled()) {
            V(msg);
        }
    }

    public void V(String msg) {
        V(tag, msg);
    }

    public void d(String msg) {
        if (isEnabled()) {
            D(msg);
        }
    }

    public void D(String msg) {
        if (alwaysPrintDebug) {
            I(tag, msg);
        } else {
            D(tag, msg);
        }
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void d(Throwable throwable) {
        if (isEnabled()) {
            D(throwable);
        }
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void D(Throwable throwable) {
        D(tag, throwable.getMessage());
    }

    public void i(String msg) {
        if (isEnabled()) {
            I(msg);
        }
    }

    public void I(String msg) {
        I(tag, msg);
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void i(Throwable throwable) {
        if (isEnabled()) {
            I(throwable);
        }
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void I(Throwable throwable) {
        I(tag, throwable.getMessage());
    }

    public void w(String msg) {
        if (enabled) {
            W(msg);
        }
    }

    public void W(String msg) {
        W(tag, msg);
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void w(Throwable throwable) {
        if (isEnabled()) {
            W(throwable);
        }
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void W(Throwable throwable) {
        W(tag, throwable.getMessage());
    }

    public void w(String msg, Throwable e) {
        if (enabled) {
            W(msg, e);
        }
    }

    public void W(String msg, Throwable e) {
        W(tag, msg, e);
    }

    public void e(String msg) {
        if (isEnabled()) {
            E(msg);
        }
    }

    public void E(String msg) {
        E(tag, msg);
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void e(Throwable throwable) {
        if (isEnabled()) {
            E(throwable);
        }
    }

    /**
     * 输出异常信息，不打印堆栈
     *
     * @param throwable
     */
    public void E(Throwable throwable) {
        E(tag, throwable.getMessage());
    }


    public void e(String msg, Throwable e) {
        if (isEnabled()) {
            E(msg, e);
        }
    }

    public void E(String msg, Throwable e) {
        E(tag, msg, e);
    }

    /**
     * 打印堆栈信息
     *
     * @param e
     */
    public void exception(Throwable e) {
        if (isEnabled()) {
            EXCEPTION(e);
        }
    }

    public void EXCEPTION(Throwable e) {
        EXCEPTION(tag, e);
    }

    public static boolean isGlobalEnabled() {
        return globalSwitch;
    }

    public static void setGlobalEnabled(boolean enabled) {
        if (enabled) {
            enableGlobal();
        } else {
            disableGlobal();
        }
    }

    public static void enableGlobal() {
        globalSwitch = true;
        D(TAG, "global log is opened");
    }

    public static void disableGlobal() {
        globalSwitch = false;
        D(TAG, "global log is closed");
    }

    public static void v(String tag, String msg) {
        if (globalSwitch) {
            Log.v(tag, msg);
        }
    }

    public static void V(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (globalSwitch) {
            D(tag, msg);
        }
    }

    public static void D(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String tag, String clazz, String msg) {
        if (globalSwitch) {
            D(tag, clazz, msg);
        }
    }

    public static void D(String tag, String clazz, String msg) {
        Log.d(tag, clazz + "-> " + msg);
    }


    public static void i(String tag, String msg) {
        if (globalSwitch) {
            I(tag, msg);
        }
    }

    public static void I(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (globalSwitch) {
            W(tag, msg);
        }
    }

    public static void W(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (globalSwitch) {
            W(tag, msg, throwable);
        }
    }

    public static void W(String tag, String msg, Throwable throwable) {
        Log.w(tag, msg, throwable);
    }

    public static void e(String tag, String msg) {
        if (globalSwitch) {
            E(tag, msg);
        }
    }

    public static void E(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (globalSwitch) {
            E(tag, msg, throwable);
        }
    }

    public static void E(String tag, String msg, Throwable throwable) {
        Log.e(tag, msg, throwable);
    }

    public static void exception(String tag, Throwable e) {
        if (globalSwitch) {
            EXCEPTION(tag, e);
        }
    }

    public static void EXCEPTION(String tag, Throwable e) {
        E(tag, e.getMessage(), e);
    }

    public static String bundlePrintString(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            stringBuilder.append(key).append(":").append(bundle.get(key)).append(",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
