package hexsook.originext.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * When executing the reflection method in ErrorlessReflection, no exceptions will be thrown at compile time and runtime.
 * If the original behavior throws an exception at runtime, when returning the required value,
 * the value is null, and when there is no return value, no operation is performed.
 * <p>
 * Note: This tool is only suitable for use when reflection exceptions do not need to be handled.
 */
public class ErrorlessReflection {

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ignore) {
        }
        return null;
    }

    public static Field getDeclaredField(Class<?> clazz, String field) {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException ignore) {
        }
        return null;
    }

    public static Method getDeclaredMethod(Class<?> clazz, String method, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(method, parameterTypes);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static Field getField(Class<?> clazz, String field) {
        try {
            return clazz.getField(field);
        } catch (NoSuchFieldException ignored) {
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String method, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(method, parameterTypes);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static void setDeclaredField(Class<?> clazz, Object instance, String field, Object value) {
        Field f = getDeclaredField(clazz, field);
        if (f == null) {
            return;
        }
        f.setAccessible(true);
        try {
            f.set(instance, value);
        } catch (IllegalAccessException ignored) {
        }
    }

    public static void setField(Class<?> clazz, Object instance, String field, Object value) {
        Field f = getField(clazz, field);
        if (f == null) {
            return;
        }
        f.setAccessible(true);
        try {
            f.set(instance, value);
        } catch (IllegalAccessException ignored) {
        }
    }

    public static Object newInstance(Class<?> clazz, Constructor<?> constructor, Object... params) {
        try {
            if (constructor == null) {
                return clazz.newInstance();
            }
            constructor.setAccessible(true);
            return constructor.newInstance(params);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException ignored) {
        }
        return null;
    }

    public static Object invokeMethod(Method method, Object instance, Object... params) {
        try {
            return method.invoke(instance, params);
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }
}
