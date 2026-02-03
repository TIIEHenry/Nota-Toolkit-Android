package nota.lang.reflect;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectCache {

    private final static ConcurrentHashMap<String, Method> CACHE_METHOD = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, Class<?>> CACHE_CLASS = new ConcurrentHashMap<>();

    //获取类的静态方法
    public static Reflect on(String className, String methodName, Class<?>... args) throws Exception {
        return new Reflect(null, getMethod(className, methodName, args));
    }

    //获取类的非静态方法
    public static Reflect on(Object object, String methodName, Class<?>... args) throws Exception {
        return new Reflect(object, getMethod(object.getClass(), methodName, args));
    }

    private static Method getMethod(String name, String methodName, Class<?>... args) throws Exception {
        return getMethod(getClass(name), methodName, args);
    }

    private static Method getMethod(Class<?> type, String methodName, Class<?>... args) throws Exception {
        String methodCacheName = getMethodCacheName(type, methodName, args);
        Method method = CACHE_METHOD.get(methodCacheName);
        if (method == null) {
            method = exactMethod(type, methodName, args);
            CACHE_METHOD.put(methodCacheName, method);
        }
        return method;
    }

    private static Method exactMethod(Class<?> type, String name, Class<?>[] types) throws Exception {
        try {
            return type.getMethod(name, types);
        } catch (NoSuchMethodException e) {
            do {
                try {
                    return type.getDeclaredMethod(name, types);
                } catch (NoSuchMethodException ignore) {
                }
                type = type.getSuperclass();
            } while (type != null);
            throw new NoSuchMethodException();
        }
    }

    private static Class<?> getClass(String name) throws Exception {
        Class<?> c = CACHE_CLASS.get(name);
        if (c == null) {
            c = Class.forName(name);
            CACHE_CLASS.put(name, c);
        }
        return c;
    }

    private static String getMethodCacheName(Class<?> type, String methodName, Class<?>... args) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(type.getName()).append(" ").append(methodName).append("(");
        for (Class<?> arg : args) {
            buffer.append(arg.getName()).append(",");
        }
        buffer.append(")");
        return buffer.toString();
    }

    public static void clearCache() {
        CACHE_METHOD.clear();
        CACHE_CLASS.clear();
    }

    public static class Reflect {

        private final Object mObject;
        private final Method mMethod;

        private Reflect(Object object, Method method) {
            mObject = object;
            mMethod = method;
        }

        @SuppressWarnings("unchecked")
        public <T> T invoke(Object... args) throws Exception {
            if (mMethod == null) {
                return null;
            }
            if (!mMethod.isAccessible()) {
                mMethod.setAccessible(true);
            }
            if (mMethod.getReturnType() == void.class) {
                mMethod.invoke(mObject, args);
                return null;
            } else {
                return (T) mMethod.invoke(mObject, args);
            }
        }

    }
}
