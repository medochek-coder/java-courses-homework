package com.kulygina.proxy;

import com.kulygina.annotation.Log;
import com.kulygina.logging.ClassForLogging;
import com.kulygina.logging.IClassForLogging;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IoC {
    static IClassForLogging createMyClassForLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new ClassForLogging());
        return (IClassForLogging) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{IClassForLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final IClassForLogging myClass;

        DemoInvocationHandler(IClassForLogging myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method methodInImpl = getImplMethod(proxy, method);
            Annotation[] declaredAnnotations = methodInImpl.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                if (annotation.annotationType().equals(Log.class)) {
                    System.out.println("executed method: " + method.getName() + getArgsAsString(args));
                }
            }
            return method.invoke(myClass, args);
        }

        private Method getImplMethod(Object proxy, Method method) throws Exception {
            DemoInvocationHandler handler = (DemoInvocationHandler) Proxy.getInvocationHandler(proxy);
            Class<? extends IClassForLogging> aClass = handler.myClass.getClass();
            Method[] allMethods = aClass.getDeclaredMethods();
            for (Method methodInImpl : allMethods) {
                if (methodInImpl.getName().equals(method.getName())) {
                    return methodInImpl;
                }
            }
            throw new Exception("Impossible exception! Method " + method.getName()
                    + " does not exist in class " + aClass.getClass().getName());
        }

        private String getArgsAsString(Object[] args) {
            String params = "";
            if (args == null) {
                return ", no param";
            }
            for (Object arg : args) {
                if (arg instanceof String[]) {
                    for (String s : (String[]) arg) {
                        params += s + ", ";
                    }
                } else {
                    params += arg.toString() + ", ";
                }
            }
            return ", param: " + params.substring(0, params.length() - 2);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
